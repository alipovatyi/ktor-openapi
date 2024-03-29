package dev.arli.openapi.model.property

import java.math.BigDecimal
import kotlin.reflect.KType

internal enum class NumberFormat(override val key: String) : DataFormat {
    NO_FORMAT(""),
    FLOAT("float"),
    DOUBLE("double")
}

internal fun KType.getNumberFormat(): NumberFormat {
    return when (classifier) {
        Float::class -> NumberFormat.FLOAT
        Double::class -> NumberFormat.DOUBLE
        BigDecimal::class -> NumberFormat.NO_FORMAT
        else -> throw IllegalArgumentException("Unsupported number type [$this]")
    }
}
