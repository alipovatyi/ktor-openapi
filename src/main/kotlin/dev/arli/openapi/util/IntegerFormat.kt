package dev.arli.openapi.util

import kotlin.reflect.KType

enum class IntegerFormat(val key: String) {
    NO_FORMAT(""),
    INT_32("int32"),
    INT_64("int64")
}

fun KType.getIntegerFormat(): IntegerFormat {
    return when (classifier) {
        Int::class -> IntegerFormat.INT_32
        Long::class -> IntegerFormat.INT_64
        else -> throw IllegalArgumentException("Unsupported integer type [$this]")
    }
}
