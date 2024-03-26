package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeGridVlineConfigurator {
    abstract val x: Property<TAutoOrInt?>
    abstract val start: Property<TInt?>
    abstract val end: Property<TIntOrNone?>
    abstract val stroke: Property<TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<TValue>?>
    abstract val position: Property<TAlignment?>
}