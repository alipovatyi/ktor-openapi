package dev.arli.openapi.model.property

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import java.io.File
import kotlin.reflect.KType

internal enum class StringFormat(override val key: String) : DataFormat {
    NO_FORMAT(""),
    DATE("date"),
    DATE_TIME("date-time"),
    PASSWORD("password"),
    BYTE("byte"),
    BINARY("binary")
}

internal fun KType.getStringFormat(): StringFormat {
    return when (classifier) {
        String::class -> StringFormat.NO_FORMAT
        LocalDate::class -> StringFormat.DATE
        LocalDateTime::class -> StringFormat.DATE_TIME
        File::class -> StringFormat.BINARY
        else -> throw IllegalArgumentException("Unsupported string type [$this]")
    }
}
