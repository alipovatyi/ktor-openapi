package dev.arli.openapi.util

import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class OpenAPIPrimitiveTypeTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return OpenAPI primitive type for supported primitive`(
        givenType: KType,
        expectedOpenAPIPrimitiveType: OpenAPIPrimitiveType
    ) {
        assertEquals(expectedOpenAPIPrimitiveType, givenType.getPrimitiveOpenAPIType())
    }

    @Test
    fun `Should throw an error for OpenAPI primitive type if primitive is not supported`() {
        val givenType = Unit::class.createType()

        assertFailsWith<IllegalArgumentException> {
            givenType.getPrimitiveOpenAPIType()
        }
    }

    private companion object {

        @JvmStatic
        fun `Should return OpenAPI primitive type for supported primitive`() = listOf(
            arguments(Int::class.createType(), OpenAPIPrimitiveType.INTEGER),
            arguments(Long::class.createType(), OpenAPIPrimitiveType.INTEGER),
            arguments(Float::class.createType(), OpenAPIPrimitiveType.NUMBER),
            arguments(Double::class.createType(), OpenAPIPrimitiveType.NUMBER),
            arguments(String::class.createType(), OpenAPIPrimitiveType.STRING),
            arguments(Boolean::class.createType(), OpenAPIPrimitiveType.BOOLEAN),
            arguments(LocalDate::class.createType(), OpenAPIPrimitiveType.STRING),
            arguments(LocalDateTime::class.createType(), OpenAPIPrimitiveType.STRING)
        )
    }
}
