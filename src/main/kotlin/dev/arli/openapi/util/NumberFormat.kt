package dev.arli.openapi.util

import kotlin.reflect.KType

enum class NumberFormat(val key: String) {
    NO_FORMAT(""),
    INT_32("int32"),
    INT_64("int64"),
    FLOAT("float"),
    DOUBLE("double")
}

fun KType.getNumberFormat(): NumberFormat {
    return when (this) {
        DataTypes.intType -> NumberFormat.INT_32
        DataTypes.nullableIntType -> NumberFormat.INT_32
        DataTypes.longType -> NumberFormat.INT_64
        DataTypes.nullableLongType -> NumberFormat.INT_64
        DataTypes.floatType-> NumberFormat.FLOAT
        DataTypes.nullableFloatType-> NumberFormat.FLOAT
        DataTypes.doubleType-> NumberFormat.DOUBLE
        DataTypes.nullableDoubleType-> NumberFormat.DOUBLE
        DataTypes.bigDecimalType-> NumberFormat.NO_FORMAT
        DataTypes.nullableBigDecimalType-> NumberFormat.NO_FORMAT
        else -> throw IllegalArgumentException("Unsupported number type [$this]")
    }
}
