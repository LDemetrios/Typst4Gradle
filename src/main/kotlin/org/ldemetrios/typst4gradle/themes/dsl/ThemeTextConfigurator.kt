package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeTextConfigurator {
    abstract val font: Property<TArrayOrStr<TStr>?>
    abstract val fallback: Property<TBool?>
    abstract val style: Property<TStr?>
    abstract val weight: Property<TIntOrStr?>
    abstract val stretch: Property<TRatio?>
    abstract val size: Property<TLength?>
    abstract val fill: Property<TColorOrGradientOrPattern?>
    abstract val stroke: Property<TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<TValue>?>
    abstract val tracking: Property<TLength?>
    abstract val spacing: Property<TRelative?>
    abstract val cjkLatinSpacing: Property<TAutoOrNone?>
    abstract val baseline: Property<TLength?>
    abstract val overhang: Property<TBool?>
    abstract val topEdge: Property<TLengthOrStr?>
    abstract val bottomEdge: Property<TLengthOrStr?>
    abstract val lang: Property<TStr?>
    abstract val region: Property<TNoneOrStr?>
    abstract val script: Property<TAutoOrStr?>
    abstract val dir: Property<TAutoOrDirection?>
    abstract val hyphenate: Property<TAutoOrBool?>
    abstract val kerning: Property<TBool?>
    abstract val alternates: Property<TBool?>
    abstract val stylisticSet: Property<TIntOrNone?>
    abstract val ligatures: Property<TBool?>
    abstract val discretionaryLigatures: Property<TBool?>
    abstract val historicalLigatures: Property<TBool?>
    abstract val numberType: Property<TAutoOrStr?>
    abstract val numberWidth: Property<TAutoOrStr?>
    abstract val slashedZero: Property<TBool?>
    abstract val fractions: Property<TBool?>
    abstract val features: Property<TArrayOrDictionary<TStr, TInt>?>
}