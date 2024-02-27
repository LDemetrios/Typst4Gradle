package org.ldemetrios.typst4gradle

import org.ldemetrios.utilities.constants.NEWLINES
import java.io.File
import java.io.IOException

fun execColored(command: List<String>, root: File? = null): ColoredExecResult {
    try {
        val builder = ProcessBuilder(command)
        if (root != null) builder.directory(root)
        val process = builder.start()

        process.outputStream.close()

        val out = process.inputStream
        val err = process.errorStream

        val marked = mutableListOf<Pair<String, String>>()

        val outWriter = LinedOutput { marked.add("output" to it) }
        val errWriter = LinedOutput { marked.add("error" to it) }

        do {
            var any = false

            val outChar = out.read()
            if (outChar != -1) {
                outWriter.write(outChar)
                any = true
            }

            val errChar = err.read()
            if (errChar != -1) {
                errWriter.write(errChar)
                any = true
            }
        } while (any)

        return ColoredExecResult(marked, process.waitFor())
    } catch (e: IOException) {
        return ColoredExecResult(listOf(), -1)
    }
}

private const val CR_CODE = '\r'.code
private const val LF_CODE = '\n'.code
private val NEWLINE_CODES = NEWLINES.map(Char::code)

class LinedOutput(val onLine: (String) -> Unit) {
    private var sb = StringBuilder()
    private var rPassed = false
    private fun send() {
        onLine(sb.toString())
        sb.clear()
    }

    fun write(p0: Int) {
        if (rPassed) {
            send()
            when (p0) {
                LF_CODE -> Unit
                CR_CODE -> Unit
                in NEWLINE_CODES -> /*One more*/ send()
                else -> sb.append(p0.toChar())
            }
        } else {
            when (p0) {
                CR_CODE -> Unit
                /*LF or*/ in NEWLINE_CODES -> send()
                else -> sb.append(p0.toChar())
            }
        }
        rPassed = p0 == CR_CODE
    }

    fun close() {
        if (sb.isNotEmpty()) {
            onLine(sb.toString())
        }
    }
}

data class ColoredExecResult(val marked: List<Pair<String, String>>, val code: Int)
