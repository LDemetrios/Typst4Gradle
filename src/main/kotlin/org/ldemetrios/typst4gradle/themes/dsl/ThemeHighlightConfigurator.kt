package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeHighlightConfigurator {
    abstract val fill: Property<TColorOrGradientOrPattern?>
    abstract val stroke: Property<TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<TValue>?>
    abstract val topEdge: Property<TLengthOrStr?>
    abstract val bottomEdge: Property<TLengthOrStr?>
    abstract val extent: Property<TLength?>
    abstract val radius: Property<TDictionaryOrRelative<TValue>?>
}