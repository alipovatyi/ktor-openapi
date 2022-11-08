package dev.arli.openapi.model.property

import java.io.File
import kotlin.reflect.KType
import java.time.LocalDate as JavaLocalDate
import java.time.LocalDateTime as JavaLocalDateTime
import java.time.LocalTime as JavaLocalTime
import java.time.OffsetDateTime as JavaOffsetDateTime
import kotlinx.datetime.LocalDate as KotlinLocalDate
import kotlinx.datetime.LocalDateTime as KotlinLocalDateTime

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
        JavaLocalDate::class -> StringFormat.DATE
        JavaLocalTime::class -> StringFormat.DATE_TIME // TODO: verify if it's a correct type for time
        JavaLocalDateTime::class -> StringFormat.DATE_TIME
        JavaOffsetDateTime::class -> StringFormat.DATE_TIME
        KotlinLocalDate::class -> StringFormat.DATE
        KotlinLocalDateTime::class -> StringFormat.DATE_TIME
        File::class -> StringFormat.BINARY
        else -> throw IllegalArgumentException("Unsupported string type [$this]")
    }
}
