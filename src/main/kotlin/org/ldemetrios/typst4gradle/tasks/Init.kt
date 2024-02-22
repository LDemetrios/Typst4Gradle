package org.ldemetrios.typst4gradle.tasks

import org.gradle.api.Project
import org.ldemetrios.typst4gradle.*
import java.io.File

fun t4gInit(project: Project, config: Typst4Gradle) {
    val src = (config.srcFolderProp.get()).toFile()
    val out = (config.outFolderProp.get()).toFile()
    val themes = (config.themesFolderProp.get()).toFile()
    val lib = (config.libFolderProp.get()).toFile()
    if (!src.exists()) src.mkdirs0()
    if (!out.exists()) out.mkdirs0()
    if (!themes.exists()) themes.mkdirs0()
    if (!lib.exists()) themes.mkdirs0()

    File("${config.themesFolderProp.get()}/usual.typ").run {
        if (exists()) return@run
        parentFile.mkdirs0()
        createNewFile0()
        writeText(
            """
               |#let background = white
               |#let foreground = black
               |#let pagewidth = none
               |#let pageheight = none
               |
            """.trimMargin()
        )
    }

    File("${config.themesFolderProp.get()}/development.typ").run {
        if (exists()) return@run
        parentFile.mkdirs0()
        createNewFile0()
        writeText(
            """
               |#let background = black
               |#let foreground = white
               |#let pagewidth = none
               |#let pageheight = auto
               |
            """.trimMargin()
        )
    }

    File("${config.libFolderProp.get()}/externation.typ").run {
        if (exists()) return@run
        parentFile.mkdirs0()
        createNewFile0()
        writeText(
            Typst4GradlePlugin::class.java
                .getResourceAsStream("/org/ldemetrios/typst4gradle/externation.typ")!!
                .bufferedReader().readText()
        )
    }

    config.themeDispatcherFileProp.get().toFile().run {
        if (exists()) return@run
        parentFile.mkdirs0()
        createNewFile0()
        writeText("#import \"/${config.themesFolderProp.get().relativize0(project.rootDir.toPath())}/usual.typ\": *\n")
    }

    config.stylingFileProp.get().toFile().run {
        if (exists()) return@run
        parentFile.mkdirs0()
        createNewFile0()
        writeText(
            """
               |#import "/${config.themeDispatcherFileProp.get().relativize0(project.rootDir.toPath())}": *
               |
               |
               |#let decide(on, whatif) = if (on == none) { body => body } else { whatif }
               |#let either(..a) = if (a.pos().contains(none)) { none } else { 1 }
               |
               |#let showtheme(
               |    base: none,
               |    fill: none,
               |    surface: none,
               |    high: none,
               |    subtle: none,
               |    overlay: none,
               |    iris: none,
               |    foam: none,
               |    fnote: none,
               |) = body => [
               |    #show: decide(base, (body) => { set page(fill: base); body })
               |    #show: decide(fill, (body) => { set text(fill: fill); body })
               |    #show:decide(subtle, (body) => { set line(stroke: subtle); body })
               |    #show : decide(either(subtle, overlay), (body) => {
               |        set circle(stroke: subtle, fill: overlay)
               |        set ellipse(stroke: subtle, fill: overlay)
               |        set path(stroke: subtle, fill: overlay)
               |        set polygon(stroke: subtle, fill: overlay)
               |        set rect(stroke: subtle, fill: overlay)
               |        set square(stroke: subtle, fill: overlay)
               |        body
               |    })
               |    #show : decide(high, (body) => { set highlight(fill: highlight.high); body })
               |    #show : decide(
               |        either(surface, high),
               |        (body) => { set table(fill: surface, stroke: highlight.high); body },
               |    )
               |  
               |    #show link: decide(iris, (body) => { set text(fill: iris); body })
               |    #show ref: decide(foam, (body) => { set text(fill: foam); body })
               |    #show footnote: decide(fnote, (body) => { set text(fill: fnote); body })
               |  
               |    #body
               |]
               |
               |#let theme-show-rule = (rest) => [
               |    #show : showtheme(
               |        base:background,
               |        fill:foreground,
               |    )
               |    
               |    #show : decide(pagewidth, body => { set page(width:pagewidth); body } )
               |    #show : decide(pageheight, body => { set page(height:pageheight); body } )
               |    
               |    #rest
               |]
               |
            """.trimMargin()
        )
    }
}