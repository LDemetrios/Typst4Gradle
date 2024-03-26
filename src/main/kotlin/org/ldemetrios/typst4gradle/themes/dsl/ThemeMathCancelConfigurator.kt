package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeMathCancelConfigurator {
    abstract val length: Property<TRelative?>
    abstract val inverted: Property<TBool?>
    abstract val cross: Property<TBool?>
    abstract val angle: Property<TAngleOrAuto?>
    abstract val stroke: Property<TColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<TValue>?>
}