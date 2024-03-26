package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeBoxConfigurator {
    abstract val width: Property<TAutoOrFractionOrRelative?>
    abstract val height: Property<TAutoOrRelative?>
    abstract val baseline: Property<TRelative?>
    abstract val fill: Property<TColorOrGradientOrNoneOrPattern?>
    abstract val stroke: Property<TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<TValue>?>
    abstract val radius: Property<TDictionaryOrRelative<TValue>?>
    abstract val inset: Property<TDictionaryOrRelative<TValue>?>
    abstract val outset: Property<TDictionaryOrRelative<TValue>?>
    abstract val clip: Property<TBool?>
    abstract val body: Property<TContentOrNone?>
}