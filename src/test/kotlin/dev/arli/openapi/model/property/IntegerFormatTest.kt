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
    fun `Should return correct key for integer format`(givenIntegerFormat: IntegerFormat, expectedKey: String) {
        assertEquals(expectedKey, givenIntegerFormat.key)
    }

    @ParameterizedTest
    @MethodSource
    fun `Should return integer format`(givenType: KType, expectedIntegerFormat: IntegerFormat) {
        assertEquals(expectedIntegerFormat, givenType.getIntegerFormat())
    }

    @Test
    fun `Should throw exception for unsupported integer type`() {
        val givenType = typeOf<String>()

        assertFailsWith<IllegalArgumentException> {
            givenType.getNumberFormat()
        }
    }

    private companion object{

        @JvmStatic
        fun `Should return correct key for integer format`() = listOf(
            arguments(IntegerFormat.NO_FORMAT, ""),
            arguments(IntegerFormat.INT_32, "int32"),
            arguments(IntegerFormat.INT_64, "int64")
        )

        @JvmStatic
        fun `Should return integer format`() = listOf(
            arguments(typeOf<Int>(), IntegerFormat.INT_32),
            arguments(typeOf<Int?>(), IntegerFormat.INT_32),
            arguments(typeOf<Long>(), IntegerFormat.INT_64),
            arguments(typeOf<Long?>(), IntegerFormat.INT_64)
        )
    }
}
