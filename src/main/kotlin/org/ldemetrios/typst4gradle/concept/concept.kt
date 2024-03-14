import TPL1Helper.Companion.git
import org.gradle.api.Task

interface Typst4Gradle {
    var outputFolder: String
    var sourceFolder: String
    var libraryFolder: String
    var themesFolder: String

    fun thirdPartyPackages(function: TPLHelper.() -> Unit) {

    }

}

interface TPLHelper {
    abstract var installation: PackageInstallation

    fun lib(function: TPL1Helper.() -> Unit) {
        TODO("Not yet implemented")
    }

}

interface TPL1Helper {
    companion object {
        const val git: String = "git"
    }

    var protocol: String
    abstract var url: String
}

fun <T : Task> Typst4Gradle.config(func: T.() -> Unit) = Unit

interface CompileAll : Task {
    var outputFormat: String
    var formats: List<String>
}

fun <T> configure(func: T.() -> Unit) = Unit

interface RemovePdfs : Task {

}

fun main() {
    configure<Typst4Gradle> {
        sourceFolder = "src"
        libraryFolder = "lib"
        outputFolder = "out"
        themesFolder = "themes"

        thirdPartyPackages {
            installation = PackageInstallation.Local
            lib {
                url = "https://github.com/..."
                protocol = git
            }
        }

        themes {
            theme ("development") {
                table.fill
                table.stroke
                highlight.fill

                overline.stroke
                strikethrough.stroke
                underline.stroke
                math.cancel.stroke
                text.fill
                block.fill
                block.stroke
                box.fill
                box.stroke
                page.fill

                circle.fill
                circle.stroke
                ellipse.fill
                ellipse.stroke
                line.fill
                line.stroke
                path.fill
                path.stroke
                polygon.fill
                polygon.stroke
                rect.fill
                rect.stroke
                square.fill
                square.stroke

//                raw {
//                }
            }

//            rawSetup
        }

        config<CompileAll> {
            formats = listOf("pdf", "png")

            outputFormat = "{format?}{-?theme&format}{theme?}/{name}.{format}"
        }


    }

}

sealed interface PackageInstallation {
    object Local : PackageInstallation
    data class Folder(val path: String) : PackageInstallation
}

