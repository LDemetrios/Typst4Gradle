package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeGridConfigurator {
    abstract val columns: Property<TArrayOrAutoOrFractionOrIntOrRelative<TValue>?>
    abstract val rows: Property<TArrayOrAutoOrFractionOrIntOrRelative<TValue>?>
    abstract val gutter: Property<TArrayOrAutoOrFractionOrIntOrRelative<TValue>?>
    abstract val columnGutter: Property<TArrayOrAutoOrFractionOrIntOrRelative<TValue>?>
    abstract val rowGutter: Property<TArrayOrAutoOrFractionOrIntOrRelative<TValue>?>
    abstract val fill: Property<TArrayOrColorOrGradientOrNoneOrPattern<TValue>?>
    abstract val align: Property<TAlignmentOrArrayOrAuto<TValue>?>
    abstract val stroke: Property<TArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<TValue, TValue>?>
    abstract val inset: Property<TArrayOrDictionaryOrRelative<TValue, TValue>?>
    @get:Nested abstract val cell : ThemeGridCellConfigurator
    @get:Nested abstract val hline : ThemeGridHlineConfigurator
    @get:Nested abstract val vline : ThemeGridVlineConfigurator
    @get:Nested abstract val header : ThemeGridHeaderConfigurator
    @get:Nested abstract val footer : ThemeGridFooterConfigurator
}