package org.ldemetrios.typst4gradle.themes

import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.typst4k.orm.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.provider.ListProperty

fun GeneralThemeConfigurator.setConventions() {
    text.fill.convention(foreground)
    page.fill.convention(background)
    circle.width.convention(ellipse.width)
    circle.height.convention(ellipse.height)
    circle.fill.convention(ellipse.fill)
    circle.stroke.convention(ellipse.stroke)
    circle.inset.convention(ellipse.inset)
    circle.outset.convention(ellipse.outset)
    circle.body.convention(ellipse.body)
    ellipse.fill.convention(geometry.fill)
    ellipse.stroke.convention(geometry.stroke)
    ellipse.inset.convention(geometry.inset)
    ellipse.outset.convention(geometry.outset)
    ellipse.body.convention(geometry.body)
    path.fill.convention(geometry.fill)
    path.stroke.convention(geometry.stroke)
    polygon.fill.convention(geometry.fill)
    polygon.stroke.convention(geometry.stroke)
    rect.fill.convention(geometry.fill)
    rect.stroke.convention(geometry.stroke)
    rect.inset.convention(geometry.inset)
    rect.outset.convention(geometry.outset)
    rect.body.convention(geometry.body)
    square.width.convention(rect.width)
    square.height.convention(rect.height)
    square.fill.convention(rect.fill)
    square.stroke.convention(rect.stroke)
    square.radius.convention(rect.radius)
    square.inset.convention(rect.inset)
    square.outset.convention(rect.outset)
    square.body.convention(rect.body)
}
