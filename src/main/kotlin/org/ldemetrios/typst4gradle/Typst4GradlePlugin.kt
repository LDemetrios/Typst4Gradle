package org.ldemetrios.typst4gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.property
import org.ldemetrios.typst4gradle.tasks.*

import org.ldemetrios.typst4k.*;
import org.ldemetrios.utilities.*;
import java.io.File
import java.nio.file.Path

private fun Property<Path>.convention(x: String) = convention(Path.of(x))

abstract class Typst4GradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val config = project.extensions.create<Typst4Gradle>("greeting", project)
        val root = project.rootDir
        config.srcFolderProp.convention("$root/typst/sources")
        config.outFolderProp.convention("$root/output")
        config.themesFolderProp.convention("$root/typst/styles/themes")
        config.themeDispatcherFileProp.convention("$root/typst/styles/themes/current-theme.typ")
        config.stylingFileProp.convention("$root/typst/styles/style.typ")
        config.outputFormatsProp.convention(listOf("pdf"))
        config.libFolderProp.convention("$root/typst/lib")

//        project.task("t4gCheckCorrectness") {
//            group = "Typst4Gradle"
//            description = "Check if configuration is correct"
//            doLast {
//                t4gCheckCorrectness(config)
//            }
//        }

        project.task("typstInit") {
            group = "Typst4Gradle"
            description = "Generated initial files for typst"
//            dependsOn("t4gCheckCorrectness")
            doLast {
                t4gInit(project, config)
            }
        }

        project.task("typstCleanExternation") {
            group = "Typst4Gradle"
            description = "Delete generated .typext files"
//            dependsOn("t4gCheckCorrectness")
            doLast {
                "find"(project.rootDir.path, "-name", "*.typext").forEach {
                    File(it).delete()
                }
            }
        }

        project.task("removePdfs") {
            group = "Typst4Gradle"
            description = "Delete output directory and automatically generated pdfs files"
//            dependsOn("t4gCheckCorrectness")
            doLast {
                config.outFolderProp.get().toFile().deleteRecursively()
                "find"(project.rootDir.toString(), "-name", "*.typ").forEach {
                    val compiled = File(it.dropLast(3) + "pdf")
                    if (compiled.exists()) compiled.delete()
                }
            }
        }

        project.task("typstProcessExternation") {
            group = "Typst4Gradle"
            description = "Process external code in typst documents"
//            dependsOn("t4gCheckCorrectness")
            dependsOn("typstCleanExternation")
            doLast {
                val sources = ("find"(config.srcFolderProp.get().toString(), "-name", "*.typ"))

                for (src in sources) {
                    project.processExternation(src);
                }
            }
        }

        project.task("typstListThemes") {
            group = "Typst4Gradle"
            description = "List all the themes present in themesFolder"
//            dependsOn("t4gCheckCorrectness")
            doLast {
                val list = listThemes(project, config)
                list.map {
                    (project.rootDir / it).relativize0(config.themesFolderProp.get())
                }.map {
                    it.toString().run { if (endsWith(".typ")) dropLast(4) else this }
                }.forEach(::println)
            }
        }

        project.task("typstCompileAll") {
            group = "Typst4Gradle"
            description = "Compiles all typst source files with available themes"
//            dependsOn("t4gCheckCorrectness")
            dependsOn("removePdfs")
            doLast {
                compileAll(
                    project,
                    config,
                    listThemes(project, config),
                    config.outputFormatsProp.get(),
                    false,
                )
            }
        }

        project.task("hello") {
            doLast {
                println(Typst.query(Path.of("./sources/test.typ"))["x"])
            }
        }
    }

}

open class Typst4Gradle(project: Project) {
    private val objects = project.objects
    val srcFolderProp: Property<Path> = objects.property()
    val outFolderProp: Property<Path> = objects.property()
    val stylingFileProp: Property<Path> = objects.property()
    val themeDispatcherFileProp: Property<Path> = objects.property()
    val themesFolderProp: Property<Path> = objects.property()
    val libFolderProp: Property<Path> = objects.property()
    val outputFormatsProp: ListProperty<String> = objects.listProperty(String::class.java)

    var srcFolder
        get() = srcFolderProp.get().toString()
        set(value) = srcFolderProp.set(Path.of(value))
    var outFolder
        get() = outFolderProp.get().toString()
        set(value) = outFolderProp.set(Path.of(value))
    var stylingFile
        get() = stylingFileProp.get().toString()
        set(value) = stylingFileProp.set(Path.of(value))
    var themeDispatcherFile
        get() = themeDispatcherFileProp.get().toString()
        set(value) = themeDispatcherFileProp.set(Path.of(value))
    var themesFolder
        get() = themesFolderProp.get().toString()
        set(value) = themesFolderProp.set(Path.of(value))
    var outputFormats
        get() = outputFormatsProp.get()
        set(value) = outputFormatsProp.set(value)
    var libFolder
        get() = libFolderProp.get()
        set(value) = libFolderProp.set(value)
}
