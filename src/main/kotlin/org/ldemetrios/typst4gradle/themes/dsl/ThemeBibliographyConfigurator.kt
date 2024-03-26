package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeBibliographyConfigurator {
    abstract val title: Property<TAutoOrContentOrNone?>
    abstract val full: Property<TBool?>
    abstract val style: Property<TStr?>
}