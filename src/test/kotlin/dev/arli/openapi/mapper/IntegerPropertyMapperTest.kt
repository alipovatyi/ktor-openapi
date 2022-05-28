package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.IntegerProperty
import dev.arli.openapi.util.IntegerFormat
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.serialization.SerialName
import org.junit.jupiter.api.Test

internal class IntegerPropertyMapperTest {

    private val mapper = IntegerPropertyMapper()

    @Test
    fun `Should map Int property to integer property`() {
        val givenProperty = TestClassWithInt::value

        val expectedIntegerProperty = IntegerProperty(
            name = "value",
            description = null,
            nullable = false,
            format = IntegerFormat.INT_32
        )

        assertEquals(expectedIntegerProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Int property to nullable integer property`() {
        val givenProperty = TestClassWithNullableInt::value

        val expectedIntegerProperty = IntegerProperty(
            name = "value",
            description = null,
            nullable = true,
            format = IntegerFormat.INT_32
        )

        assertEquals(expectedIntegerProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map Long property to integer property`() {
        val givenProperty = TestClassWithLong::value

        val expectedIntegerProperty = IntegerProperty(
            name = "value",
            description = null,
            nullable = false,
            format = IntegerFormat.INT_64
        )

        assertEquals(expectedIntegerProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Long property to nullable integer property`() {
        val givenProperty = TestClassWithNullableLong::value

        val expectedIntegerProperty = IntegerProperty(
            name = "value",
            description = null,
            nullable = true,
            format = IntegerFormat.INT_64
        )

        assertEquals(expectedIntegerProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to integer property with custom name`() {
        val givenProperty = TestClassWithCustomSerialName::value

        val expectedIntegerProperty = IntegerProperty(
            name = "custom-value",
            description = null,
            nullable = false,
            format = IntegerFormat.INT_32
        )

        assertEquals(expectedIntegerProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to integer property with description`() {
        val givenProperty = TestClassWithDescription::value

        val expectedIntegerProperty = IntegerProperty(
            name = "value",
            description = "Description",
            nullable = false,
            format = IntegerFormat.INT_32
        )

        assertEquals(expectedIntegerProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should throw exception for unsupported integer type`() {
        val givenProperty = TestClassWithInvalidType::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithInt(val value: Int)

    private data class TestClassWithNullableInt(val value: Int?)

    private data class TestClassWithLong(val value: Long)

    private data class TestClassWithNullableLong(val value: Long?)

    private data class TestClassWithCustomSerialName(@SerialName("custom-value") val value: Int)

    private data class TestClassWithDescription(@Description("Description") val value: Int)

    private data class TestClassWithInvalidType(val value: Float)
}
