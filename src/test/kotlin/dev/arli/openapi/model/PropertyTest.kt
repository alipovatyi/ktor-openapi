package dev.arli.openapi.model

import dev.arli.openapi.util.DataType
import dev.arli.openapi.util.IntegerFormat
import dev.arli.openapi.util.NumberFormat
import dev.arli.openapi.util.StringFormat
import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class PropertyTest {

    @ParameterizedTest
    @MethodSource
    fun `Should set correct data type`(givenProperty: Property, expectedDataType: DataType) {
        assertEquals(expectedDataType, givenProperty.type)
    }

    private companion object {

        @JvmStatic
        fun `Should set correct data type`() = listOf(
            arguments(StringProperty("", null, false, StringFormat.NO_FORMAT), DataType.STRING),
            arguments(NumberProperty("", null, false, NumberFormat.NO_FORMAT), DataType.NUMBER),
            arguments(IntegerProperty("", null, false, IntegerFormat.INT_32), DataType.INTEGER),
            arguments(BooleanProperty("", null, false), DataType.BOOLEAN),
            arguments(ArrayProperty("", null, false), DataType.ARRAY),
            arguments(ObjectProperty("", null, false), DataType.OBJECT)
        )
    }
}
