package dev.arli.openapi.util

import java.math.BigDecimal
import kotlin.reflect.KType
import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class DataTypeTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return data type`(givenType: KType, expectedDataType: DataType) {
        assertEquals(expectedDataType, givenType.getDataType())
    }

    private companion object {

        @JvmStatic
        fun `Should return data type`() = listOf(
            arguments(typeOf<String>(), DataType.STRING),
            arguments(typeOf<LocalDate>(), DataType.STRING),
            arguments(typeOf<LocalDateTime>(), DataType.STRING),
            arguments(typeOf<Float>(), DataType.NUMBER),
            arguments(typeOf<Double>(), DataType.NUMBER),
            arguments(typeOf<BigDecimal>(), DataType.NUMBER),
            arguments(typeOf<Int>(), DataType.INTEGER),
            arguments(typeOf<Long>(), DataType.INTEGER),
            arguments(typeOf<Boolean>(), DataType.BOOLEAN),
            arguments(typeOf<Array<*>>(), DataType.ARRAY),
            arguments(typeOf<List<*>>(), DataType.ARRAY),
            arguments(typeOf<Set<*>>(), DataType.ARRAY),
            arguments(typeOf<Map<*, *>>(), DataType.OBJECT),
            arguments(typeOf<Any>(), DataType.OBJECT),
        )
    }
}