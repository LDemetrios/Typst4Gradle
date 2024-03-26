package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeRotateConfigurator {
    abstract val pos: Property<TAngle?>
    abstract val origin: Property<TAlignment?>
    abstract val reflow: Property<TBool?>
}