package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.property.BooleanProperty
import kotlinx.serialization.SerialName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class BooleanPropertyMapperTest {

    private val mapper = BooleanPropertyMapper()

    @Test
    fun `Should map Boolean property to boolean property`() {
        val givenProperty = TestClassWithBoolean::value

        val expectedBooleanProperty = BooleanProperty(
            name = "value",
            description = null,
            nullable = false
        )

        assertEquals(expectedBooleanProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Boolean property to nullable boolean property`() {
        val givenProperty = TestClassWithNullableBoolean::value

        val expectedBooleanProperty = BooleanProperty(
            name = "value",
            description = null,
            nullable = true
        )

        assertEquals(expectedBooleanProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to boolean property with custom name`() {
        val givenProperty = TestClassWithCustomSerialName::value

        val expectedBooleanProperty = BooleanProperty(
            name = "custom-value",
            description = null,
            nullable = false
        )

        assertEquals(expectedBooleanProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to boolean property with description`() {
        val givenProperty = TestClassWithDescription::value

        val expectedBooleanProperty = BooleanProperty(
            name = "value",
            description = "Description",
            nullable = false
        )

        assertEquals(expectedBooleanProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should throw exception for unsupported boolean type`() {
        val givenProperty = TestClassWithInvalidType::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithBoolean(val value: Boolean)

    private data class TestClassWithNullableBoolean(val value: Boolean?)

    private data class TestClassWithCustomSerialName(@SerialName("custom-value") val value: Boolean)

    private data class TestClassWithDescription(@Description("Description") val value: Boolean)

    private data class TestClassWithInvalidType(val value: String)
}
