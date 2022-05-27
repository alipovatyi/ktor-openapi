package dev.arli.openapi.util

import dev.arli.openapi.util.OpenAPIPrimitiveTypes.booleanType
import dev.arli.openapi.util.OpenAPIPrimitiveTypes.doubleType
import dev.arli.openapi.util.OpenAPIPrimitiveTypes.floatType
import dev.arli.openapi.util.OpenAPIPrimitiveTypes.intType
import dev.arli.openapi.util.OpenAPIPrimitiveTypes.localDateTimeType
import dev.arli.openapi.util.OpenAPIPrimitiveTypes.localDateType
import dev.arli.openapi.util.OpenAPIPrimitiveTypes.longType
import dev.arli.openapi.util.OpenAPIPrimitiveTypes.stringType
import kotlin.reflect.KType

enum class OpenAPIPrimitiveFormat(val key: String) {
    INT_32("int32"),
    INT_64("int64"),
    FLOAT("float"),
    DOUBLE("double"),
    STRING(""),
    BOOLEAN(""),
    DATE("date"),
    DATE_TIME("date-time")
}

fun KType.getPrimitiveOpenAPIFormat(): OpenAPIPrimitiveFormat {
    return when (this) {
        intType -> OpenAPIPrimitiveFormat.INT_32
        longType -> OpenAPIPrimitiveFormat.INT_64
        floatType -> OpenAPIPrimitiveFormat.FLOAT
        doubleType -> OpenAPIPrimitiveFormat.DOUBLE
        stringType -> OpenAPIPrimitiveFormat.STRING
        booleanType -> OpenAPIPrimitiveFormat.BOOLEAN
        localDateType -> OpenAPIPrimitiveFormat.DATE
        localDateTimeType -> OpenAPIPrimitiveFormat.DATE_TIME
        else -> throw IllegalArgumentException("Unsupported OpenAPI primitive type [$this]")
    }
}
