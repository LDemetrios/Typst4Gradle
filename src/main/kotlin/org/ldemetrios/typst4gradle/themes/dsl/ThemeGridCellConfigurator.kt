package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeGridCellConfigurator {
    abstract val x: Property<TAutoOrInt?>
    abstract val y: Property<TAutoOrInt?>
    abstract val colspan: Property<TInt?>
    abstract val rowspan: Property<TInt?>
    abstract val fill: Property<TAutoOrColorOrGradientOrNoneOrPattern?>
    abstract val align: Property<TAlignmentOrAuto?>
    abstract val inset: Property<TAutoOrDictionaryOrRelative<TValue>?>
    abstract val stroke: Property<TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<TValue>?>
    abstract val breakable: Property<TAutoOrBool?>
}