package parsers

import java.io.File

data class TypeParameter(val param: Type, val variance: String) {
    override fun toString(): String = if (variance == "") "$param" else "$variance $param"
}

sealed interface Type {
    fun isGeneric(): Boolean
}

data class ConcreteType(val name: String, val params: List<TypeParameter> = listOf()) : Type {
    override fun toString(): String = if (params.isEmpty()) name else "$name<${params.joinToString(", ")}>"
    override fun isGeneric(): Boolean = params.isNotEmpty()
}

data class UnionType(val variants: List<Type>) : Type {
    override fun toString(): String = variants.joinToString(" | ")
    fun flatten(): UnionType {
        val variants = mutableListOf<Type>()
        for (variant in this.variants) {
            when (variant) {
                is UnionType -> variants.addAll(variant.flatten().variants)
                else -> variants.add(variant)
            }
        }
        return UnionType(variants)
    }

    fun flattenSingle(): Type = when {
        variants.size == 1 -> variants[0]
        variants.any { it is AnyType } -> AnyType
        else -> this
    }

    override fun isGeneric(): Boolean = variants.any { it.isGeneric() }
}

data object AnyType : Type {
    override fun toString(): String = "*"
    override fun isGeneric(): Boolean = false
}

data class Parameter(val name: List<String>, val type: Type, val convention: List<String>?) {
    override fun toString(): String =
        name.joinToString(".") + " : $type" +
                (convention?.let { " -> ${it.joinToString(".")}" } ?: "")
}

class ModelParser(data: String) : BaseParser(data) {
    val parameters: MutableList<Parameter> = mutableListOf()

    override fun lookup(value: String): Boolean {
        skipWhitespace()
        return super.lookup(value).also { skipWhitespace() }
    }

    fun parse() {
        skipWhitespace()
        while (!eof()) {
            val name = parseName()
            if(listOf("circle","radius") == name) {
                println()
            }
            lookup(":")
            val type = parseUnionType().flatten().flattenSingle()
            val convention = if (lookup("->")) {
                parseName()
            } else null
            parameters.add(Parameter(name, type, convention))
            lookup(",")
            skipWhitespace()
        }
    }

    private fun parseUnionType(): UnionType {
        val variants = mutableListOf<Type>(parseConcreteType())
        while (lookup("|")) {
            variants.add(parseConcreteType())
        }
        val (flattened) = UnionType(variants).flatten()
        return UnionType(flattened)
    }

    private fun parseConcreteType(): ConcreteType {
        val ident = parseIdentifier()
        val generic = lookup("<")
        val params = if (generic) {
            val params = mutableListOf<TypeParameter>()
            while (!lookup(">")) {
                params.add(parseTypeParameter())
                lookup(",")
            }
            params
        } else listOf()
        return ConcreteType(ident, params)
    }

    private fun parseTypeParameter(): TypeParameter {
        if (lookup("*")) return TypeParameter(AnyType, "")
        val variance = if (lookup("out ")) "out" else if (lookup("in ")) "in" else ""
        val param = parseUnionType()
        return TypeParameter(param.flatten().flattenSingle(), variance)
    }

    private fun parseName(): List<String> {
        skipWhitespace()
        val sb = StringBuilder()
        while (test { "$it".matches(Regex("[a-zA-Z\\-.]")) }) {
            sb.append(take())
        }
        return sb.toString().split(".")

    }

    private fun parseIdentifier(): String {
        skipWhitespace()
        val sb = StringBuilder()
        while (test { "$it".matches(Regex("[a-zA-Z\\-]")) }) {
            sb.append(take())
        }
        return sb.toString()

    }
}

class CommentsRemover(data: String) : BaseParser(data) {
    val result = StringBuilder()

    fun parse() {
        while (!eof()) {
            if (take('/')) {
                if (!eof() && take(/*another*/ '/')) {
                    while (!take('\n')) take()
                    result.append('\n')
                } else if (!eof() && take('*')) {
                    blockCommentUntilEnd()
                } else {
                    result.append('/')
                }
            } else result.append(take())
        }
    }

    private fun blockCommentUntilEnd() {
        while (!eof()) {
            if (take('*')) {
                if (!eof() && take('/')) {
                    return
                }
            } else if (take('/')) {
                if (!eof() && take('*')) {
                    blockCommentUntilEnd()
                }
            }
        }
    }
}