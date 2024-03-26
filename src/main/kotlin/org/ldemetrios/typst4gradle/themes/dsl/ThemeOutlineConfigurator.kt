package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeOutlineConfigurator {
    abstract val title: Property<TAutoOrContentOrNone?>
    abstract val target: Property<TLabelOrLocationOrSelector?>
    abstract val depth: Property<TIntOrNone?>
    abstract val indent: Property<TAutoOrBoolOrNoneOrRelative?>
    abstract val fill: Property<TContentOrNone?>
}