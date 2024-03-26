package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeParConfigurator {
    abstract val leading: Property<TLength?>
    abstract val justify: Property<TBool?>
    abstract val linebreaks: Property<TAutoOrStr?>
    abstract val firstLineIndent: Property<TLength?>
    abstract val hangingIndent: Property<TLength?>
}