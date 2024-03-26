package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeUnderlineConfigurator {
    abstract val stroke: Property<TAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<TValue>?>
    abstract val offset: Property<TAutoOrLength?>
    abstract val extent: Property<TLength?>
    abstract val evade: Property<TBool?>
    abstract val background: Property<TBool?>
}