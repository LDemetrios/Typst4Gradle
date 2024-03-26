package org.ldemetrios.typst4gradle.dsl

import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

data class Compilation(val theme: String?, val format: String?, val path: String, val file: String)

abstract class CompileConfigurator(val project: Project) {
    abstract val ignored: ListProperty<String>
    abstract val ignoredRegexes: ListProperty<Regex>
    abstract val formatting: Property<Compilation.() -> String>

    fun ignore(vararg strings: String) {
        ignored.addAll(strings.toList())
    }

    fun ignore(vararg regexes: Regex) {
        ignoredRegexes.addAll(regexes.toList())
    }

    fun format(action: Compilation.() -> String) {
        formatting.set(action)
    }

    internal fun isIgnored(string: String) = ignored.get().contains(string) || ignoredRegexes.get().any { it.matches(string) }

    fun setConventions() {
        ignored.convention(listOf())
        ignoredRegexes.convention(listOf())
        formatting.convention {
            val category = listOfNotNull(theme, format).joinToString("-")
            "$path/$category/$file.$format"
        }
    }
}
