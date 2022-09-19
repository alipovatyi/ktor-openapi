package dev.arli.openapi.model.property

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import java.io.File
import java.math.BigDecimal
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

internal enum class DataType(val key: String) {
    STRING("string"),
    NUMBER("number"),
    INTEGER("integer"),
    BOOLEAN("boolean"),
    ARRAY("array"),
    MAP("object"),
    OBJECT("object"),
    ENUM("string")
}

internal fun <T : Any> getDataType(clazz: KClass<T>): DataType {
    return if (clazz.isSubclassOf(Enum::class)) {
        DataType.ENUM
    } else {
        when (clazz) {
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
            // Map
            Map::class -> DataType.MAP
            // Object
            else -> DataType.OBJECT
        }
    }
}
