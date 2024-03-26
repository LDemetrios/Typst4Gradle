package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeSmartquoteConfigurator {
    abstract val double: Property<TBool?>
    abstract val enabled: Property<TBool?>
    abstract val alternative: Property<TBool?>
    abstract val quotes: Property<TArrayOrAutoOrDictionaryOrStr<TValue, TValue>?>
}