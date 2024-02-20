package org.ldemetrios.typst4gradle

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.create


abstract class MyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create<GreetingPluginExtension>("greeting")
        extension.name.convention("World")

        project.task("hello") {
            doLast {
                println("Hello ${extension.name.get()}")
            }
        }
    }
}

interface GreetingPluginExtension {
    val name: Property<String>
}
