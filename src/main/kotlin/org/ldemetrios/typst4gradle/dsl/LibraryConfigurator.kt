package org.ldemetrios.typst4gradle.dsl

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

abstract class LibraryConfigurator(val project: Project) {
    abstract val libraries: NamedDomainObjectContainer<SingleLibraryConfigurator>
    abstract val defaultOnChange: Property<OnChange>
    abstract val allFile: Property<String>
    abstract val previewLibraries: ListProperty<Pair<String, Boolean>>

    fun library(configureAction: Action<in SingleLibraryConfigurator>) {
        libraries.create("library" + libraries.size) {
            configureAction.execute(this)
            setConventions(this@LibraryConfigurator)
        }
    }

    fun preview(name: String, asModule:Boolean = false) {
        previewLibraries.add(name to asModule)
    }

    fun setConventions() {
        allFile.convention("all.typ")
        defaultOnChange.convention(OnChange.IGNORE)
        previewLibraries.convention(listOf())
    }
}

abstract class SingleLibraryConfigurator@javax.inject.Inject constructor(val name: String, val project: Project) {
    abstract val url: Property<String>
    abstract val version: Property<String>
    abstract val alias: Property<String>
    abstract val onChange: Property<OnChange>
    abstract val asModule: Property<Boolean>

    fun setConventions(parent: LibraryConfigurator) {
        this.onChange.convention(parent.defaultOnChange)
        asModule.convention(false)
    }
}

enum class OnChange {
    IGNORE, DISCARD, COMMIT
}
