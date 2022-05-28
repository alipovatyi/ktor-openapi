package dev.arli.openapi.model.property

import kotlin.reflect.KType
import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class IntegerFormatTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return number format`(givenType: KType, expectedIntegerFormat: IntegerFormat) {
        assertEquals(expectedIntegerFormat, givenType.getIntegerFormat())
    }

    @Test
    fun `Should throw exception for unsupported number type`() {
        val givenType = typeOf<String>()

        assertFailsWith<IllegalArgumentException> {
            givenType.getNumberFormat()
        }
    }

    private companion object{

        @JvmStatic
        fun `Should return number format`() = listOf(
            arguments(typeOf<Int>(), IntegerFormat.INT_32),
            arguments(typeOf<Int?>(), IntegerFormat.INT_32),
            arguments(typeOf<Long>(), IntegerFormat.INT_64),
            arguments(typeOf<Long?>(), IntegerFormat.INT_64)
        )
    }
}
