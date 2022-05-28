package dev.arli.openapi.util

import kotlin.reflect.KType
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

enum class StringFormat(val key: String) {
    NO_FORMAT(""),
    DATE("date"),
    DATE_TIME("date-time"),
    PASSWORD("password"),
    BYTE("byte"),
    BINARY("binary")
}

fun KType.getStringFormat(): StringFormat {
    return when (classifier) {
        String::class -> StringFormat.NO_FORMAT
        LocalDate::class -> StringFormat.DATE
        LocalDateTime::class -> StringFormat.DATE_TIME
        else -> throw IllegalArgumentException("Unsupported string type [$this]")
    }
}
