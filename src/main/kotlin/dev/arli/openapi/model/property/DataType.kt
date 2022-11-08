package dev.arli.openapi.model.property

import java.io.File
import java.math.BigDecimal
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import java.time.LocalDate as JavaLocalDate
import java.time.LocalDateTime as JavaLocalDateTime
import java.time.LocalTime as JavaLocalTime
import java.time.OffsetDateTime as JavaOffsetDateTime
import kotlinx.datetime.LocalDate as KotlinLocalDate
import kotlinx.datetime.LocalDateTime as KotlinLocalDateTime

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
            JavaLocalDate::class -> DataType.STRING
            JavaLocalTime::class -> DataType.STRING
            JavaLocalDateTime::class -> DataType.STRING
            JavaOffsetDateTime::class -> DataType.STRING
            KotlinLocalDate::class -> DataType.STRING
            KotlinLocalDateTime::class -> DataType.STRING
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
