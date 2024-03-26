package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeHeadingConfigurator {
    abstract val level: Property<TAutoOrInt?>
    abstract val depth: Property<TInt?>
    abstract val offset: Property<TInt?>
    abstract val numbering: Property<TNoneOrStr?>
    abstract val supplement: Property<TAutoOrContentOrNone?>
    abstract val outlined: Property<TBool?>
    abstract val bookmarked: Property<TAutoOrBool?>
}