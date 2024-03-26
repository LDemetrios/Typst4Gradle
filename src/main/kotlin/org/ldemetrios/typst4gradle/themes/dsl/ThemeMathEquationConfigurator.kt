package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeMathEquationConfigurator {
    abstract val block: Property<TBool?>
    abstract val numbering: Property<TNoneOrStr?>
    abstract val numberAlign: Property<TAlignment?>
    abstract val supplement: Property<TAutoOrContentOrNone?>
}