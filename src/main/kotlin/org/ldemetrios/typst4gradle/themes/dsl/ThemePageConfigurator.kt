package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemePageConfigurator {
    abstract val paper: Property<TStr?>
    abstract val width: Property<TAutoOrLength?>
    abstract val height: Property<TAutoOrLength?>
    abstract val flipped: Property<TBool?>
    abstract val margin: Property<TAutoOrDictionaryOrRelative<TValue>?>
    abstract val binding: Property<TAlignmentOrAuto?>
    abstract val columns: Property<TInt?>
    abstract val fill: Property<TColorOrGradientOrNoneOrPattern?>
    abstract val numbering: Property<TNoneOrStr?>
    abstract val numberAlign: Property<TAlignment?>
    abstract val header: Property<TContentOrNone?>
    abstract val headerAscent: Property<TRelative?>
    abstract val footer: Property<TContentOrNone?>
    abstract val footerDescent: Property<TRelative?>
    abstract val background: Property<TContentOrNone?>
    abstract val foreground: Property<TContentOrNone?>
}