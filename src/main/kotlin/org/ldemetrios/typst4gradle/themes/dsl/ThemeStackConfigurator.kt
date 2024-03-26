package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeStackConfigurator {
    abstract val dir: Property<TDirection?>
    abstract val spacing: Property<TFractionOrNoneOrRelative?>
}