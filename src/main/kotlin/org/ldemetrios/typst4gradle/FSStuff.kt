package org.ldemetrios.typst4gradle

import org.ldemetrios.utilities.invoke
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

fun Path.mkdirs0() = "mkdir"("-p", this.toString())

fun Path.createNewFile0() = "touch"(this.toString())

fun File.mkdirs0() = "mkdir"("-p", this.toString())

fun File.createNewFile0() = "touch"(this.toString())

fun Path.relativize0(other: Path): Path {
    val thisStr = this.toRealPath().toString().split(File.separator)
    val otherStr = other.toRealPath().toString().split(File.separator)
    var common = 0
    while (common < thisStr.size && common < otherStr.size && thisStr[common] == otherStr[common]) common++

    return Path.of("../".repeat(otherStr.size - common) + thisStr.drop(common).joinToString(File.separator))
}

operator fun Path.div(x: Path) = Path.of(this.toString() + File.separator + x.toString())
operator fun Path.div(x: File) = Path.of(this.toString() + File.separator + x.toString())
operator fun File.div(x: Path) = Path.of(this.toString() + File.separator + x.toString())
operator fun File.div(x: File) = Path.of(this.toString() + File.separator + x.toString())
