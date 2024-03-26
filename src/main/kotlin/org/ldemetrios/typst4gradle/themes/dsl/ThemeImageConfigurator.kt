package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeImageConfigurator {
    abstract val format: Property<TAutoOrStr?>
    abstract val width: Property<TAutoOrRelative?>
    abstract val height: Property<TAutoOrRelative?>
    abstract val alt: Property<TNoneOrStr?>
    abstract val fit: Property<TStr?>
}