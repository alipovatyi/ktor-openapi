package dev.arli.openapi.util

import kotlin.reflect.KType

enum class StringFormat(val key: String) {
    NO_FORMAT(""),
    DATE("date"),
    DATE_TIME("date-time"),
    PASSWORD("password"),
    BYTE("byte"),
    BINARY("binary")
}

fun KType.getStringFormat(): StringFormat {
    return when (this) {
        DataTypes.stringType -> StringFormat.NO_FORMAT
        DataTypes.localDateType -> StringFormat.DATE
        DataTypes.localDateTimeType -> StringFormat.DATE_TIME
        else -> throw IllegalArgumentException("Unsupported string type [$this]")
    }
}
