package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeTermsConfigurator {
    abstract val tight: Property<TBool?>
    abstract val separator: Property<TContent?>
    abstract val indent: Property<TLength?>
    abstract val hangingIndent: Property<TLength?>
    abstract val spacing: Property<TAutoOrFractionOrRelative?>
}