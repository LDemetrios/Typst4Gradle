package org.ldemetrios.typst4gradle.dsl

import org.gradle.api.Project
import org.ldemetrios.typst4gradle.Typst4GradleConfigurator
import org.ldemetrios.typst4gradle.div
import java.io.File


fun cleanAll(project: Project, config: Typst4GradleConfigurator) {
    val sourceFolder = File(project.rootDir / config.sourceFolder.get())
    val themesFolder = File(project.rootDir / config.themesConf.folder.get())

    sequenceOf(sourceFolder, themesFolder).flatMap(File::walkTopDown)
        .filter { it.extension in listOf("pdf", "svg", "png") }
        .map { it to it.relativeTo(sourceFolder) }
        .filter { !config.cleanConf.isIgnored(it.second.toString()) }
        .forEach {
            println("Deleting ${it.first}")
            it.first.delete().also(::println)
        }
}

