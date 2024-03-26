package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeFigureConfigurator {
    abstract val placement: Property<TAlignmentOrAutoOrNone?>
    abstract val caption: Property<TContentOrNone?>
    abstract val kind: Property<TAutoOrStr?>
    abstract val supplement: Property<TAutoOrContentOrNone?>
    abstract val numbering: Property<TNoneOrStr?>
    abstract val gap: Property<TLength?>
    abstract val outlined: Property<TBool?>
}