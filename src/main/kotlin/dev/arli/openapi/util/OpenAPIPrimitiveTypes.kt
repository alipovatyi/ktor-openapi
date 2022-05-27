package dev.arli.openapi.util

import kotlin.reflect.typeOf
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

object OpenAPIPrimitiveTypes {
    val intType = typeOf<Int>()
    val longType = typeOf<Long>()
    val floatType = typeOf<Float>()
    val doubleType = typeOf<Double>()
    val stringType = typeOf<String>()
    val booleanType = typeOf<Boolean>()
    val localDateType = typeOf<LocalDate>()
    val localDateTimeType = typeOf<LocalDateTime>()
}
