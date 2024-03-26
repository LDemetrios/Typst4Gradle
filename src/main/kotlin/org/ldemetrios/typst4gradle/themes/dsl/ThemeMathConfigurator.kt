package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class ThemeMathConfigurator {
    @get:Nested abstract val accent : ThemeMathAccentConfigurator
    @get:Nested abstract val attach : ThemeMathAttachConfigurator
    @get:Nested abstract val cancel : ThemeMathCancelConfigurator
    @get:Nested abstract val cases : ThemeMathCasesConfigurator
    @get:Nested abstract val equation : ThemeMathEquationConfigurator
    @get:Nested abstract val lr : ThemeMathLrConfigurator
    @get:Nested abstract val mat : ThemeMathMatConfigurator
    @get:Nested abstract val root : ThemeMathRootConfigurator
    @get:Nested abstract val underbrace : ThemeMathUnderbraceConfigurator
    @get:Nested abstract val overbrace : ThemeMathOverbraceConfigurator
    @get:Nested abstract val underbracket : ThemeMathUnderbracketConfigurator
    @get:Nested abstract val overbracket : ThemeMathOverbracketConfigurator
    @get:Nested abstract val vec : ThemeMathVecConfigurator
}