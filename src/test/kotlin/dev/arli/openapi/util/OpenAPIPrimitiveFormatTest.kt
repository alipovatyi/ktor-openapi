package dev.arli.openapi.util

import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class OpenAPIPrimitiveFormatTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return OpenAPI primitive format for supported primitive`(
        givenType: KType,
        expectedOpenAPIPrimitiveFormat: OpenAPIPrimitiveFormat
    ) {
        assertEquals(expectedOpenAPIPrimitiveFormat, givenType.getPrimitiveOpenAPIFormat())
    }

    @Test
    fun `Should throw an error for OpenAPI primitive format if primitive is not supported`() {
        val givenType = Unit::class.createType()

        assertFailsWith<IllegalArgumentException> {
            givenType.getPrimitiveOpenAPIFormat()
        }
    }

    private companion object {

        @JvmStatic
        fun `Should return OpenAPI primitive format for supported primitive`() = listOf(
            arguments(Int::class.createType(), OpenAPIPrimitiveFormat.INT_32),
            arguments(Long::class.createType(), OpenAPIPrimitiveFormat.INT_64),
            arguments(Float::class.createType(), OpenAPIPrimitiveFormat.FLOAT),
            arguments(Double::class.createType(), OpenAPIPrimitiveFormat.DOUBLE),
            arguments(String::class.createType(), OpenAPIPrimitiveFormat.STRING),
            arguments(Boolean::class.createType(), OpenAPIPrimitiveFormat.BOOLEAN),
            arguments(LocalDate::class.createType(), OpenAPIPrimitiveFormat.DATE),
            arguments(LocalDateTime::class.createType(), OpenAPIPrimitiveFormat.DATE_TIME)
        )
    }
}