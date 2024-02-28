package org.ldemetrios.typst4gradle.tasks

import kotlinx.serialization.Serializable
import org.gradle.api.Project
import org.gradle.process.internal.ExecException
import org.ldemetrios.typst4gradle.ColoredExecResult
import org.ldemetrios.typst4gradle.createNewFile0
import org.ldemetrios.typst4gradle.execColored
import org.ldemetrios.typst4gradle.mkdirs0
import org.ldemetrios.typst4k.TElement
import org.ldemetrios.typst4k.Typst
import java.io.File
import java.nio.file.Path

@Serializable
data class ExtData(val files: Map<String, String>, val commands: List<List<String>>)

fun Project.processExternation(source: String) {
    val query = Typst.query(Path.of(source), root = rootDir.toPath())
    val resultsFile = File("${source}ext")

    if (resultsFile.exists()) resultsFile.delete()


    val execCallsNumber = query.getAs<TElement.TMetadata<Int>>("exec-calls-number")
        .singleOrNull() ?: return
    println("Found ${execCallsNumber.value} code fragments")

    val dir = File("__tmp__")

    val executionResults = mutableListOf<List<ColoredExecResult>>()

    for (i in 0 until execCallsNumber.value) {
        val fragmentExecutionResults = mutableListOf<ColoredExecResult>()

        val fragment = query.getAs<TElement.TMetadata<ExtData>>("exec-call-$i").single().value

        if (dir.exists()) dir.deleteRecursively()

        for ((filename, content) in fragment.files) {
            val file = File("$dir/$filename")
            file.parentFile.mkdirs0()
            file.createNewFile0()
            file.writeText(content)
        }

        for (command in fragment.commands) {
            try {
                println(command)
                val res = execColored(command, root = dir)

                fragmentExecutionResults.add(res)
            } catch (e: ExecException) {
                throw Throwable("Unexpected", e)
            }
        }

        dir.deleteRecursively()

        executionResults.add(fragmentExecutionResults)
    }


    fun String.toCode() = "\"" +
            this
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t") + "\""

    resultsFile.run {
        if (exists()) delete()
        parentFile.mkdirs0()
        createNewFile0()
        writeText(
            executionResults.joinToString(" \n", "(\n", "\n)") {
                it.joinToString("\n\t", "\t(\n", "\n\t),") {
                    """
                    |       (
                    |           output: (
                    |${it.marked.joinToString ("\n"){
                                    """
                    |                (    
                    |                |                    color: "${it.first}",
                    |                |                    line:${it.second.toCode()},
                    |                |                ), """.trimMargin()
                                    }}                                
                    |            ),
                    |            code: ${it.code}
                    |        ),
                    """.trimMargin()
                }
            }
        )
    }
}

