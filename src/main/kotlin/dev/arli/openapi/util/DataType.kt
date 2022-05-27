package dev.arli.openapi.util

import java.math.BigDecimal
import kotlin.reflect.KType
import kotlin.reflect.typeOf
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

enum class DataType(val key: String) {
    STRING("string"),
    NUMBER("number"),
    INTEGER("integer"),
    BOOLEAN("boolean"),
    ARRAY("array"),
    OBJECT("object")
}

fun KType.getDataType(): DataType {
    return when (this) {
        DataTypes.intType -> DataType.INTEGER
        DataTypes.longType -> DataType.INTEGER
        DataTypes.floatType -> DataType.NUMBER
        DataTypes.doubleType -> DataType.NUMBER
        DataTypes.booleanType -> DataType.BOOLEAN
        DataTypes.stringType -> DataType.STRING
        DataTypes.localDateType -> DataType.STRING
        DataTypes.localDateTimeType -> DataType.STRING
        DataTypes.arrayType -> DataType.ARRAY
        DataTypes.listType -> DataType.ARRAY
        DataTypes.setType -> DataType.ARRAY
        DataTypes.mapType -> DataType.OBJECT
        else -> DataType.OBJECT
    }
}

object DataTypes {
    val intType = typeOf<Int>()
    val longType = typeOf<Long>()
    val floatType = typeOf<Float>()
    val doubleType = typeOf<Double>()
    val bigDecimalType = typeOf<BigDecimal>()
    val booleanType = typeOf<Boolean>()
    val stringType = typeOf<String>()
    val localDateType = typeOf<LocalDate>()
    val localDateTimeType = typeOf<LocalDateTime>()
    val arrayType = typeOf<Array<*>>()
    val listType = typeOf<List<*>>()
    val setType = typeOf<Set<*>>()
    val mapType = typeOf<Map<*, *>>()
}
