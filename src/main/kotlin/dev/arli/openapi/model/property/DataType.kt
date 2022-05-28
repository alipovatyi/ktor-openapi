package dev.arli.openapi.model.property

import java.math.BigDecimal
import kotlin.reflect.KType
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
    return when (classifier) {
        // Integer
        Int::class -> DataType.INTEGER
        Long::class -> DataType.INTEGER
        // Number
        Float::class -> DataType.NUMBER
        Double::class -> DataType.NUMBER
        BigDecimal::class -> DataType.NUMBER
        // Boolean
        Boolean::class -> DataType.BOOLEAN
        // String
        String::class -> DataType.STRING
        LocalDate::class -> DataType.STRING
        LocalDateTime::class -> DataType.STRING
        // Array
        Array::class -> DataType.ARRAY
        List::class -> DataType.ARRAY
        Set::class -> DataType.ARRAY
        // Object
        Map::class -> DataType.OBJECT
        else -> throw IllegalArgumentException("Unsupported data type [$this]")
    }
}
