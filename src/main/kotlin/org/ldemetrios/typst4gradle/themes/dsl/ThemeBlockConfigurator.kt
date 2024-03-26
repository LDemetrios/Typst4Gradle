package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeBlockConfigurator {
    abstract val width: Property<TAutoOrRelative?>
    abstract val height: Property<TAutoOrRelative?>
    abstract val breakable: Property<TBool?>
    abstract val fill: Property<TColorOrGradientOrNoneOrPattern?>
    abstract val stroke: Property<TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<TValue>?>
    abstract val radius: Property<TDictionaryOrRelative<TValue>?>
    abstract val inset: Property<TDictionaryOrRelative<TValue>?>
    abstract val outset: Property<TDictionaryOrRelative<TValue>?>
    abstract val spacing: Property<TFractionOrRelative?>
    abstract val above: Property<TFractionOrRelative?>
    abstract val below: Property<TFractionOrRelative?>
    abstract val clip: Property<TBool?>
    abstract val body: Property<TContentOrNone?>
}