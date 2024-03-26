package org.ldemetrios.typst4gradle.dsl

import org.gradle.api.Project
import org.ldemetrios.typst4gradle.Typst4GradleConfigurator
import org.ldemetrios.typst4gradle.div
import org.ldemetrios.typst4k.Typst
import org.ldemetrios.typst4k.TypstCompilerResult
import java.io.File

fun compileAll(project: Project, config: Typst4GradleConfigurator) {
    val sourceFolder = File(project.rootDir / config.sourceFolder.get())
    val outputFolder = File(project.rootDir / config.outputFolder.get())

    val sources = sourceFolder.walkTopDown()
        .filter { it.extension == "typ" }
        .map { it.relativeTo(sourceFolder) }
        .filter { !config.compileConf.isIgnored(it.toString()) }

    val typst = Typst(config.executable.get())
    var success = true
    val formatting = config.compileConf.formatting.get()
    sources.forEach { source ->
        val parent = source.parent ?: ""
        val name = source.nameWithoutExtension
        for (theme in config.themesConf.themes) {
            for (format in config.formats.get()) {
                val output = outputFolder / Compilation(theme.name, format, parent, name).formatting()
                File(output).parentFile.mkdirs()
//                println("\"$sourceFolder/$source\", $output, $format")
                val res = typst.compile(
                    "$sourceFolder/$source",
                    root = project.rootDir.absolutePath,
                    output = output,
                    inputs = mapOf("theme" to theme.name),
                    format = format
                )
                if (res is TypstCompilerResult.Failure) {
                    success = false
                    System.err.println("Typst compilation failed for $source")
                    System.err.println(res.message)
                    System.err.println("\n")
                }
            }
        }
    }
    if (!success) {
        throw AssertionError("Typst compilation failed")
    }
}

