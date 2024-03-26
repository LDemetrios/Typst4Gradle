package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemePlaceConfigurator {
    abstract val alignment: Property<TAlignmentOrAuto?>
    abstract val float: Property<TBool?>
    abstract val clearance: Property<TLength?>
    abstract val dx: Property<TRelative?>
    abstract val dy: Property<TRelative?>
}