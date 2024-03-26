package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeMathMatConfigurator {
    abstract val delim: Property<TNoneOrStr?>
    abstract val augment: Property<TDictionaryOrIntOrNone<TValue>?>
    abstract val gap: Property<TRelative?>
    abstract val rowGap: Property<TRelative?>
    abstract val columnGap: Property<TRelative?>
}