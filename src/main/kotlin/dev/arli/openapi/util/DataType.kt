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
        // Integer
        DataTypes.intType -> DataType.INTEGER
        DataTypes.nullableIntType -> DataType.INTEGER
        DataTypes.longType -> DataType.INTEGER
        DataTypes.nullableLongType -> DataType.INTEGER
        // Number
        DataTypes.floatType -> DataType.NUMBER
        DataTypes.nullableFloatType -> DataType.NUMBER
        DataTypes.doubleType -> DataType.NUMBER
        DataTypes.nullableDoubleType -> DataType.NUMBER
        DataTypes.bigDecimalType -> DataType.NUMBER
        DataTypes.nullableBigDecimalType -> DataType.NUMBER
        // Boolean
        DataTypes.booleanType -> DataType.BOOLEAN
        DataTypes.nullableBooleanType -> DataType.BOOLEAN
        // String
        DataTypes.stringType -> DataType.STRING
        DataTypes.nullableStringType -> DataType.STRING
        DataTypes.localDateType -> DataType.STRING
        DataTypes.nullableLocalDateType -> DataType.STRING
        DataTypes.localDateTimeType -> DataType.STRING
        DataTypes.nullableLocalDateTimeType -> DataType.STRING
        // Array
        DataTypes.arrayType -> DataType.ARRAY
        DataTypes.nullableArrayType -> DataType.ARRAY
        DataTypes.listType -> DataType.ARRAY
        DataTypes.nullableListType -> DataType.ARRAY
        DataTypes.setType -> DataType.ARRAY
        DataTypes.nullableSetType -> DataType.ARRAY
        // Object
        DataTypes.mapType -> DataType.OBJECT
        DataTypes.nullableMapType -> DataType.OBJECT
        else -> DataType.OBJECT
    }
}

object DataTypes {
    val intType = typeOf<Int>()
    val nullableIntType = typeOf<Int?>()
    val longType = typeOf<Long>()
    val nullableLongType = typeOf<Long?>()
    val floatType = typeOf<Float>()
    val nullableFloatType = typeOf<Float?>()
    val doubleType = typeOf<Double>()
    val nullableDoubleType = typeOf<Double?>()
    val bigDecimalType = typeOf<BigDecimal>()
    val nullableBigDecimalType = typeOf<BigDecimal?>()
    val booleanType = typeOf<Boolean>()
    val nullableBooleanType = typeOf<Boolean?>()
    val stringType = typeOf<String>()
    val nullableStringType = typeOf<String?>()
    val localDateType = typeOf<LocalDate>()
    val nullableLocalDateType = typeOf<LocalDate?>()
    val localDateTimeType = typeOf<LocalDateTime>()
    val nullableLocalDateTimeType = typeOf<LocalDateTime?>()
    val arrayType = typeOf<Array<*>>()
    val nullableArrayType = typeOf<Array<*>?>()
    val listType = typeOf<List<*>>()
    val nullableListType = typeOf<List<*>?>()
    val setType = typeOf<Set<*>>()
    val nullableSetType = typeOf<Set<*>?>()
    val mapType = typeOf<Map<*, *>>()
    val nullableMapType = typeOf<Map<*, *>?>()
}
