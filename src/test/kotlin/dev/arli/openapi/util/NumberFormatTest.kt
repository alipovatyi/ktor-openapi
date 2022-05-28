package dev.arli.openapi.util

import java.math.BigDecimal
import kotlin.reflect.KType
import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class NumberFormatTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return number format`(givenType: KType, expectedNumberFormat: NumberFormat) {
        assertEquals(expectedNumberFormat, givenType.getNumberFormat())
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
            arguments(typeOf<Int>(), NumberFormat.INT_32),
            arguments(typeOf<Int?>(), NumberFormat.INT_32),
            arguments(typeOf<Long>(), NumberFormat.INT_64),
            arguments(typeOf<Long?>(), NumberFormat.INT_64),
            arguments(typeOf<Double>(), NumberFormat.DOUBLE),
            arguments(typeOf<Double?>(), NumberFormat.DOUBLE),
            arguments(typeOf<Float>(), NumberFormat.FLOAT),
            arguments(typeOf<Float?>(), NumberFormat.FLOAT),
            arguments(typeOf<BigDecimal>(), NumberFormat.NO_FORMAT),
            arguments(typeOf<BigDecimal?>(), NumberFormat.NO_FORMAT)
        )
    }
}
