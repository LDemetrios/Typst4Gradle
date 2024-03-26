package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeMathAttachConfigurator {
    abstract val t: Property<TContentOrNone?>
    abstract val b: Property<TContentOrNone?>
    abstract val tl: Property<TContentOrNone?>
    abstract val bl: Property<TContentOrNone?>
    abstract val tr: Property<TContentOrNone?>
    abstract val br: Property<TContentOrNone?>
}