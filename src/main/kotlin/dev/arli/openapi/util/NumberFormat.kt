package dev.arli.openapi.util

import java.math.BigDecimal
import kotlin.reflect.KType

enum class NumberFormat(val key: String) {
    NO_FORMAT(""),
    FLOAT("float"),
    DOUBLE("double")
}

fun KType.getNumberFormat(): NumberFormat {
    return when (classifier) {
        Float::class -> NumberFormat.FLOAT
        Double::class -> NumberFormat.DOUBLE
        BigDecimal::class -> NumberFormat.NO_FORMAT
        else -> throw IllegalArgumentException("Unsupported number type [$this]")
    }
}
