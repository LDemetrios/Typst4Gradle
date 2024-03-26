package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

abstract class GeneralThemeConfigurator @javax.inject.Inject constructor(val name: String) {
    abstract val foreground: Property<TColorOrGradientOrPattern?>
    abstract val background: Property<TColorOrGradientOrPattern?>
    @get:Nested abstract val bibliography : ThemeBibliographyConfigurator
    @get:Nested abstract val cite : ThemeCiteConfigurator
    @get:Nested abstract val figure : ThemeFigureConfigurator
    @get:Nested abstract val footnote : ThemeFootnoteConfigurator
    @get:Nested abstract val h : ThemeHConfigurator
    @get:Nested abstract val v : ThemeVConfigurator
    @get:Nested abstract val heading : ThemeHeadingConfigurator
    @get:Nested abstract val highlight : ThemeHighlightConfigurator
    @get:Nested abstract val linebreak : ThemeLinebreakConfigurator
    @get:Nested abstract val outline : ThemeOutlineConfigurator
    @get:Nested abstract val overline : ThemeOverlineConfigurator
    @get:Nested abstract val par : ThemeParConfigurator
    @get:Nested abstract val quote : ThemeQuoteConfigurator
    @get:Nested abstract val raw : ThemeRawConfigurator
    @get:Nested abstract val ref : ThemeRefConfigurator
    @get:Nested abstract val smartquote : ThemeSmartquoteConfigurator
    @get:Nested abstract val strike : ThemeStrikeConfigurator
    @get:Nested abstract val strong : ThemeStrongConfigurator
    @get:Nested abstract val subscript : ThemeSubConfigurator
    @get:Nested abstract val superscript : ThemeSuperConfigurator
    @get:Nested abstract val underline : ThemeUnderlineConfigurator
    @get:Nested abstract val table : ThemeTableConfigurator
    @get:Nested abstract val text : ThemeTextConfigurator
    @get:Nested abstract val list : ThemeListConfigurator
    @get:Nested abstract val terms : ThemeTermsConfigurator
    @get:Nested abstract val enum : ThemeEnumConfigurator
    @get:Nested abstract val document : ThemeDocumentConfigurator
    @get:Nested abstract val math : ThemeMathConfigurator
    @get:Nested abstract val align : ThemeAlignConfigurator
    @get:Nested abstract val block : ThemeBlockConfigurator
    @get:Nested abstract val box : ThemeBoxConfigurator
    @get:Nested abstract val colbreak : ThemeColbreakConfigurator
    @get:Nested abstract val columns : ThemeColumnsConfigurator
    @get:Nested abstract val grid : ThemeGridConfigurator
    @get:Nested abstract val move : ThemeMoveConfigurator
    @get:Nested abstract val pad : ThemePadConfigurator
    @get:Nested abstract val page : ThemePageConfigurator
    @get:Nested abstract val place : ThemePlaceConfigurator
    @get:Nested abstract val rotate : ThemeRotateConfigurator
    @get:Nested abstract val scale : ThemeScaleConfigurator
    @get:Nested abstract val stack : ThemeStackConfigurator
    @get:Nested abstract val image : ThemeImageConfigurator
    @get:Nested abstract val line : ThemeLineConfigurator
    @get:Nested abstract val circle : ThemeCircleConfigurator
    @get:Nested abstract val ellipse : ThemeEllipseConfigurator
    @get:Nested abstract val path : ThemePathConfigurator
    @get:Nested abstract val polygon : ThemePolygonConfigurator
    @get:Nested abstract val rect : ThemeRectConfigurator
    @get:Nested abstract val square : ThemeSquareConfigurator
    @get:Nested abstract val geometry : ThemeGeometryConfigurator
}