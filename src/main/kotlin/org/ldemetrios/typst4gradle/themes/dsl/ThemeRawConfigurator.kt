package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeRawConfigurator {
    abstract val block: Property<TBool?>
    abstract val lang: Property<TNoneOrStr?>
    abstract val align: Property<TAlignment?>
    abstract val syntaxes: Property<TArrayOrStr<TValue>?>
    abstract val theme: Property<TNoneOrStr?>
    abstract val tabSize: Property<TInt?>
}