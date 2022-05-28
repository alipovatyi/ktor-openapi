package dev.arli.openapi.util

import java.math.BigDecimal
import kotlin.reflect.KType

enum class NumberFormat(val key: String) {
    NO_FORMAT(""),
    INT_32("int32"),
    INT_64("int64"),
    FLOAT("float"),
    DOUBLE("double")
}

fun KType.getNumberFormat(): NumberFormat {
    return when (classifier) {
        Int::class -> NumberFormat.INT_32
        Long::class -> NumberFormat.INT_64
        Float::class -> NumberFormat.FLOAT
        Double::class -> NumberFormat.DOUBLE
        BigDecimal::class -> NumberFormat.NO_FORMAT
        else -> throw IllegalArgumentException("Unsupported number type [$this]")
    }
}
