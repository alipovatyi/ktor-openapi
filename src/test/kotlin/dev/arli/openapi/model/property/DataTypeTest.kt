package dev.arli.openapi.model.property

import java.math.BigDecimal
import kotlin.reflect.KType
import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class DataTypeTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return data type`(givenType: KType, expectedDataType: DataType) {
        assertEquals(expectedDataType, givenType.getDataType())
    }

    @Test
    fun `Should throw exception for unsupported data type`() {
        val givenType = typeOf<Any>()

        assertFailsWith<IllegalArgumentException> {
            givenType.getDataType()
        }
    }

    @Test
    fun `Should throw exception for unsupported nullable data type`() {
        val givenType = typeOf<Any?>()

        assertFailsWith<IllegalArgumentException> {
            givenType.getDataType()
        }
    }

    private companion object {

        @JvmStatic
        fun `Should return data type`() = listOf(
            arguments(typeOf<String>(), DataType.STRING),
            arguments(typeOf<String?>(), DataType.STRING),
            arguments(typeOf<LocalDate>(), DataType.STRING),
            arguments(typeOf<LocalDate?>(), DataType.STRING),
            arguments(typeOf<LocalDateTime>(), DataType.STRING),
            arguments(typeOf<LocalDateTime?>(), DataType.STRING),
            arguments(typeOf<Float>(), DataType.NUMBER),
            arguments(typeOf<Float?>(), DataType.NUMBER),
            arguments(typeOf<Double>(), DataType.NUMBER),
            arguments(typeOf<Double?>(), DataType.NUMBER),
            arguments(typeOf<BigDecimal>(), DataType.NUMBER),
            arguments(typeOf<BigDecimal?>(), DataType.NUMBER),
            arguments(typeOf<Int>(), DataType.INTEGER),
            arguments(typeOf<Int?>(), DataType.INTEGER),
            arguments(typeOf<Long>(), DataType.INTEGER),
            arguments(typeOf<Long?>(), DataType.INTEGER),
            arguments(typeOf<Boolean>(), DataType.BOOLEAN),
            arguments(typeOf<Boolean?>(), DataType.BOOLEAN),
            arguments(typeOf<Array<*>>(), DataType.ARRAY),
            arguments(typeOf<Array<*>?>(), DataType.ARRAY),
            arguments(typeOf<List<*>>(), DataType.ARRAY),
            arguments(typeOf<List<*>?>(), DataType.ARRAY),
            arguments(typeOf<Set<*>>(), DataType.ARRAY),
            arguments(typeOf<Set<*>?>(), DataType.ARRAY),
            arguments(typeOf<Map<*, *>>(), DataType.OBJECT),
            arguments(typeOf<Map<*, *>?>(), DataType.OBJECT),
            arguments(typeOf<Enum<*>>(), DataType.ENUM),
            arguments(typeOf<Enum<*>?>(), DataType.ENUM)
        )
    }
}