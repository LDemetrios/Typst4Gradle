package org.ldemetrios.typst4gradle

import org.gradle.api.Action
import java.io.File

operator fun <T> Action<in T>?.plus(other: Action<in T>?): Action<T> {
    val a = this
    val b = other
    return Action<T> { a?.execute(this); b?.execute(this) }
}

operator fun File.div(child: String) = "$this/$child"

