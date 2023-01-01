package dev.arli.openapi.model.property

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal
import kotlin.reflect.KType
import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class NumberFormatTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return correct key for number format`(givenNumberFormat: NumberFormat, expectedKey: String) {
        assertEquals(expectedKey, givenNumberFormat.key)
    }

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

    private companion object {

        @JvmStatic
        fun `Should return correct key for number format`() = listOf(
            arguments(NumberFormat.NO_FORMAT, ""),
            arguments(NumberFormat.FLOAT, "float"),
            arguments(NumberFormat.DOUBLE, "double")
        )

        @JvmStatic
        fun `Should return number format`() = listOf(
            arguments(typeOf<Double>(), NumberFormat.DOUBLE),
            arguments(typeOf<Double?>(), NumberFormat.DOUBLE),
            arguments(typeOf<Float>(), NumberFormat.FLOAT),
            arguments(typeOf<Float?>(), NumberFormat.FLOAT),
            arguments(typeOf<BigDecimal>(), NumberFormat.NO_FORMAT),
            arguments(typeOf<BigDecimal?>(), NumberFormat.NO_FORMAT)
        )
    }
}
