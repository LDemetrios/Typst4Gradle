package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeEnumConfigurator {
    abstract val tight: Property<TBool?>
    abstract val numbering: Property<TStr?>
    abstract val start: Property<TInt?>
    abstract val full: Property<TBool?>
    abstract val indent: Property<TLength?>
    abstract val bodyIndent: Property<TLength?>
    abstract val spacing: Property<TAutoOrFractionOrRelative?>
    abstract val numberAlign: Property<TAlignment?>
}