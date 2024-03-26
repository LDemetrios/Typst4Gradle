package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeDocumentConfigurator {
    abstract val title: Property<TContentOrNone?>
    abstract val author: Property<TArrayOrStr<TStr>?>
    abstract val keywords: Property<TArrayOrStr<TStr>?>
    abstract val date: Property<TAutoOrDatetimeOrNone?>
}