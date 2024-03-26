package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemePadConfigurator {
    abstract val left: Property<TRelative?>
    abstract val top: Property<TRelative?>
    abstract val right: Property<TRelative?>
    abstract val bottom: Property<TRelative?>
    abstract val x: Property<TRelative?>
    abstract val y: Property<TRelative?>
    abstract val rest: Property<TRelative?>
}