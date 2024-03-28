package org.ldemetrios.typst4gradle.dsl

import org.gradle.api.Project
import org.gradle.internal.impldep.org.tomlj.Toml
import org.ldemetrios.typst4gradle.Typst4GradleConfigurator
import org.ldemetrios.typst4gradle.div
import org.ldemetrios.utilities.*
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*


private enum class LibraryProvider {
    HTTPS_GIT // Extend later
}

fun loadLibraries(project: Project, config: Typst4GradleConfigurator) {
    val librariesFolder = File(project.rootDir / config.librariesFolder.get())
    if (!librariesFolder.exists()) librariesFolder.mkdirs()

    val libraries = config.librariesConf.libraries

    val loaded = libraries.map {
        val provider = when {
            it.url.get().matches(Regex("https://.*\\.git")) -> LibraryProvider.HTTPS_GIT
            else -> throw AssertionError("Unsupported URL: ${it.url.get()}")
        }
        it to provider
    }.mapNotNull { (it, provider) ->
        val alias = resolveAlias(it, provider)

        val success = when (provider) {
            LibraryProvider.HTTPS_GIT -> {
                loadGit(
                    it.url.get(),
                    alias,
                    it.version.get(),
                    it.onChange.get(),
                    config.gitExecutable.get(),
                    librariesFolder
                )
            }
        }

        if (success) alias to it.asModule.get() else null
    }

    val code = loaded.joinToString ("") { "#import \"${it.first}.typ\"" + if (it.second) "\n" else ": *\n" } + "\n" +
            config.librariesConf.previewLibraries.get()
                .joinToString ("") { "#import \"@preview/${it.first}\"" + if (it.second) "\n" else ": *\n" } + "\n"

    File(librariesFolder / config.librariesConf.allFile.get()).writeText(code)
}

fun loadGit(
    url: String,
    alias: String,
    version: String,
    onChange: OnChange,
    gitExecutable: String,
    librariesFolder: File
): Boolean {
    fun git(vararg args: Any?) = exec(listOf(gitExecutable, *args.map { it.toString() }.toTypedArray()))

    val folder = librariesFolder / alias
    if (!File(folder).exists()) {
        val (out, err, exit) = git("-C", librariesFolder, "clone", url, alias)
        if (exit != 0) {
            println("Unable to load $alias ($url): \n" + err.joinToString("\n"))
            return false
        }
    }

    val changed = git("-C", folder, "status", "-s").output.joinToString("").isNotBlank()

    if (changed) when (onChange) {
        OnChange.IGNORE -> Unit
        OnChange.DISCARD -> git("-C", folder, "reset", "--hard")
        OnChange.COMMIT -> {
            val date = sdf.format(Date())
            git("-C", folder, "add", ".")
            git("-C", folder, "commit", "--allow-empty", "-m", "Automatically captured updates on $date")
        }
    }

    val (out, err, exit) = git("-C", folder, "pull", "--rebase")
    if (exit != 0) {
        println("Unable to pull $alias ($url): \n" + err.joinToString("\n"))
        return false
    }

    val toml = File(folder / version / "typst.toml").readText()
    val entry = toml.split(Regex("entrypoint\\s*=\\s*\""))[1].split(Regex("[\r\n]"))[0].trim().dropLast(1)
        .replaceAll(
            mapOf("\\\\" to "\\\\", "\\\"" to "\"", "\\n" to "\n", "\\\t" to "\t", "\\\r" to "\r")
        ) // God damn it, fix later


    val file = File(librariesFolder / alias + ".typ")
    file.writeText("#import \"$alias/$version/$entry\": *\n")
    return true

//    val someValue: String = toml.getString("someKey")
//    val someDate: Date = toml.getDate("someTable.someDate")
//    val myClass: MyClass = toml.to(MyClass::class.java)


}

private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

private fun resolveAlias(it: SingleLibraryConfigurator, provider: LibraryProvider): String =
    it.alias.orNull ?: when (provider) {
        LibraryProvider.HTTPS_GIT -> {
            val x = it.url.get().split("/") as Iterable<String>
            x.last().dropLast(4)
        }
    }

