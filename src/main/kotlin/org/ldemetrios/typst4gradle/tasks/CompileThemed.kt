package org.ldemetrios.typst4gradle.tasks

import org.gradle.api.Project
import org.ldemetrios.typst4gradle.*
import org.ldemetrios.typst4k.TElement
import org.ldemetrios.typst4k.Typst
import org.ldemetrios.utilities.TerminalException
import org.ldemetrios.utilities.invoke
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.nameWithoutExtension

fun Path.isSubfileOf(another: Path) = this.toRealPath().toString().startsWith(another.toRealPath().toString())

fun listThemes(project: Project, config: Typst4Gradle): List<Path> {
    val exclude = listOf(
        config.themeDispatcherFileProp.get().toRealPath(),
        config.stylingFileProp.get().toRealPath(),
    )
    return config.themesFolderProp.get()
        .toRealPath()
        .listDirectoryEntries()
        .map(Path::toRealPath)
        .filter { it !in exclude }
        .map { it.relativize0(project.rootDir.toPath()) }
}

fun compileAll(
    project: Project,
    config: Typst4Gradle,
    themes: List<Path>,
    formats: List<String> = listOf("pdf"),
    separateFormats: Boolean = false
) {
    val dispatcher = config.themeDispatcherFileProp.get().toFile()
    val wasTheme = dispatcher.run { if (exists()) readText() else null }

    dispatcher.run {
        if (!exists()) {
            parentFile.mkdirs0()
            createNewFile0()
        }
    }

    val exclude = listOf(
        config.themeDispatcherFileProp.get().toRealPath(),
        config.stylingFileProp.get().toRealPath(),
    )

    val failed = mutableListOf<String>()
    val sources = "find"(config.srcFolderProp.get().toString(), "-name", "*.typ")
        .filter {
            if (Path.of(it).toRealPath() in exclude) return@filter false
            if (themes.any { jt -> Files.isSameFile(Path.of(it), jt) }) return@filter false
            try {
                Typst.query(Path.of(it), root = project.rootDir.toPath())
                    .getAs<TElement.TMetadata<Boolean>>("do-not-render").isEmpty()
            } catch (e: TerminalException) {
                failed.add(it)
                false
            }
        }


    for (format in formats) {
        for (theme in themes) {
            dispatcher.writeText("#import \"/$theme\": *\n")
            val themeName = theme.nameWithoutExtension
            for (source in sources) {
                val out = StringBuilder(config.outFolderProp.get().toString())
                out.append("/")

                if (separateFormats) out.append(format).append("/")

                out.append(themeName).append("/")
                out.append(Path.of(source).relativize0(config.srcFolderProp.get()).toString().dropLast(4))
                out.append(".").append(format)

                Path.of(out.toString()).parent.mkdirs0()
                Typst.compile(Path.of(source), root = project.rootDir.toPath(), output = Path.of(out.toString()))
            }
        }
    }

    dispatcher.run {
        if (wasTheme == null) {
            delete()
        } else {
            writeText(wasTheme)
        }
    }

    if (failed.isNotEmpty()) {
        throw AssertionError("Failed to query/compile $failed")
    }
}