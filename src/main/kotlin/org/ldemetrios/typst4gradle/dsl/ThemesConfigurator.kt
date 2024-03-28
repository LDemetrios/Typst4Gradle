package org.ldemetrios.typst4gradle.dsl

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.ldemetrios.typst4gradle.plus
import org.ldemetrios.typst4gradle.themes.GeneralThemeConfigurator
import org.ldemetrios.typst4gradle.themes.generateRule
import org.ldemetrios.typst4gradle.themes.setConventions
import java.io.File

abstract class ThemesConfigurator(val project: Project) {
    abstract val folder: Property<String>
    abstract val default: Property<String>

    abstract val themes: NamedDomainObjectContainer<GeneralThemeConfigurator>

    abstract val allLastConf: Property<Action<in GeneralThemeConfigurator>>

    fun allLast(configureAction: Action<in GeneralThemeConfigurator>) {
        allLastConf.set(allLastConf.orNull + configureAction)
    }

    abstract val allFirstConf: Property<Action<in GeneralThemeConfigurator>>

    fun allFirst(configureAction: Action<in GeneralThemeConfigurator>) {
        allFirstConf.set(allFirstConf.orNull + configureAction)
    }

    fun theme(name: String, configureAction: Action<in GeneralThemeConfigurator>) {
        themes.create(name) {
            allFirstConf.orNull?.execute(this)
            configureAction.execute(this)
            allLastConf.orNull?.execute(this)
            setConventions()
        }
    }

    fun setConventions() {
        folder.convention("sty")
    }
}

fun initThemes(project: Project, config: ThemesConfigurator) {
    val relative = "/" + config.folder.get()
    val folder = project.rootDir.path + relative
    File(folder).run {
        if (exists()) delete()
        mkdirs()
    }
    config.themes.forEach {
        File("$folder/${it.name}.typ").run {
            writeText(it.generateRule())
        }
    }
    val names = config.themes.map { it.name }
    val code = StringBuilder()
    for (name in names) {
        code.append("""#import "$relative/$name.typ": theme as $name-theme""")
        code.append("\n")
    }
    code.append("\n")
    code.append("#let themes-dict = (\n")
    for (name in names) {
        code.append("""  "$name": $name-theme,""")
        code.append("\n")
    }
    code.append(")\n\n")
    if (config.default.isPresent) {
        code.append(
            """
                |#let theme = themes-dict.at(
                |   sys.inputs.at(
                |       "theme",
                |       default:"${config.default.get()}"
                |   ), default: it => it,
                |)
            """.trimMargin()
        )
        code.append("\n")
        File("$folder/theme.typ").run {
            parentFile.mkdirs()
            writeText(code.toString())
        }
    }
}