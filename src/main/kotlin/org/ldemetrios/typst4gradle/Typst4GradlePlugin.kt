package org.ldemetrios.typst4gradle

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.ldemetrios.typst4gradle.dsl.*


abstract class Typst4GradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val config = project.extensions.create<Typst4GradleConfigurator>("typst4gradle", project)
        config.setConventions()

        project.task("initThemes") {
            group = "Typst4Gradle"
            description = "Initialize themes folder"
            doLast {
                initThemes(project, config.themesConf)
            }
        }

        project.task("compileTypst") {
            group = "Typst4Gradle"
            description = "Compile all typst sources"
            dependsOn("cleanTypst")
            dependsOn("initThemes")
            doLast {
                compileAll(project, config)
            }
        }

        project.task("cleanTypst") {
            group = "Typst4Gradle"
            description = "Compile all typst sources"
            doLast {
                loadLibraries(project, config)
            }
        }

        project.task("typstLoadLibraries") {
            group = "Typst4Gradle"
            description = "Load (and possibly refresh specified libraries)"
            doLast {
                loadLibraries(project, config)
            }
        }
    }
}

fun Project.typst4gradle(configuration: Typst4GradleConfigurator.() -> Unit): Unit =
    this.configure<Typst4GradleConfigurator>(configuration)

abstract class Typst4GradleConfigurator(val project: Project) {
    abstract val executable: Property<String>
    abstract val gitExecutable: Property<String>
    abstract val outputFolder: Property<String>
    abstract val sourceFolder: Property<String>
    abstract val librariesFolder: Property<String>
    abstract val formats: ListProperty<String>

    @get:Nested
    abstract val themesConf: ThemesConfigurator

    fun themes(action: Action<in ThemesConfigurator>) {
        action.execute(themesConf)
    }

    @get:Nested
    abstract val compileConf: CompileConfigurator

    fun compile(action: Action<in CompileConfigurator>) {
        action.execute(compileConf)
    }

    @get:Nested
    abstract val cleanConf: CleanConfigurator

    fun clean(action: Action<in CleanConfigurator>) {
        action.execute(cleanConf)
    }

    @get:Nested
    abstract val librariesConf: LibraryConfigurator

    fun libraries(action: Action<in LibraryConfigurator>) {
        action.execute(librariesConf)
    }

    fun setConventions() {
        outputFolder.convention("typst")
        outputFolder.convention("out")
        sourceFolder.convention("src")
        librariesFolder.convention("lib")
        gitExecutable.convention("git")
        formats.convention(listOf("pdf"))
        themesConf.setConventions()
        compileConf.setConventions()
        cleanConf.setConventions()
        librariesConf.setConventions()
    }
}

