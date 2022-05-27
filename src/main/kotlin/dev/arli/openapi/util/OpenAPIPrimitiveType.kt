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

fun KType.getPrimitiveOpenAPIType(): OpenAPIPrimitiveType {
    return when (this) {
        intType -> OpenAPIPrimitiveType.INTEGER
        longType -> OpenAPIPrimitiveType.INTEGER
        floatType -> OpenAPIPrimitiveType.NUMBER
        doubleType -> OpenAPIPrimitiveType.NUMBER
        stringType -> OpenAPIPrimitiveType.STRING
        booleanType -> OpenAPIPrimitiveType.BOOLEAN
        localDateType -> OpenAPIPrimitiveType.STRING
        localDateTimeType -> OpenAPIPrimitiveType.STRING
        else -> throw IllegalArgumentException("Unsupported OpenAPI primitive type [$this]")
    }
}

enum class OpenAPIPrimitiveType(val key: String) {
    INTEGER("integer"),
    NUMBER("number"),
    STRING("string"),
    BOOLEAN("boolean")
}
