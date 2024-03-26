package org.ldemetrios.typst4gradle.dsl

import org.gradle.api.Project
import org.gradle.api.provider.ListProperty

abstract class CleanConfigurator(val project: Project) {
    abstract val ignored: ListProperty<String>
    abstract val ignoredRegexes: ListProperty<Regex>

    fun ignore(vararg strings: String) {
        ignored.addAll(strings.toList())
    }

    fun ignore(vararg regexes: Regex) {
        ignoredRegexes.addAll(regexes.toList())
    }

    internal fun isIgnored(string: String) = ignored.get().contains(string) || ignoredRegexes.get().any { it.matches(string) }

    fun setConventions() {
        ignored.convention(listOf())
        ignoredRegexes.convention(listOf())
    }
}
