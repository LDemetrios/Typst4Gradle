package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemePathConfigurator {
    abstract val fill: Property<TColorOrGradientOrNoneOrPattern?>
    abstract val stroke: Property<TAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<TValue>?>
    abstract val closed: Property<TBool?>
    abstract val vertices: Property<TArray<TArray<TValue>>?>
}