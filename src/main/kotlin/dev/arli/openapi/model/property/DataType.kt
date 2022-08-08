package dev.arli.openapi.model.property

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import java.io.File
import java.math.BigDecimal
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

enum class DataType(val key: String) {
    STRING("string"),
    NUMBER("number"),
    INTEGER("integer"),
    BOOLEAN("boolean"),
    ARRAY("array"),
    OBJECT("object"),
    ENUM("string")
}

fun <T : Any> getDataType(clazz: KClass<T>): DataType {
    return if (clazz.isSubclassOf(Enum::class)) {
        DataType.ENUM
    } else when (clazz) {
        // Integer
        Int::class -> DataType.INTEGER
        Long::class -> DataType.INTEGER
        // Number
        Float::class -> DataType.NUMBER
        Double::class -> DataType.NUMBER
        BigDecimal::class -> DataType.NUMBER
        // Boolean
        Boolean::class -> DataType.BOOLEAN
        // Enum
        Enum::class -> DataType.ENUM
        // String
        String::class -> DataType.STRING
        LocalDate::class -> DataType.STRING
        LocalDateTime::class -> DataType.STRING
        File::class -> DataType.STRING
        // Array
        Array::class -> DataType.ARRAY
        List::class -> DataType.ARRAY
        Set::class -> DataType.ARRAY
        // Object
        Map::class -> DataType.OBJECT
        else -> DataType.OBJECT
    }
}
