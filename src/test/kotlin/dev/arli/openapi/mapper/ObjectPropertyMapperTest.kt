package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.property.ObjectProperty
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.serialization.SerialName
import org.junit.jupiter.api.Test

internal class ObjectPropertyMapperTest {

    private val mapper = ObjectPropertyMapper()

    @Test
    fun `Should map Map property to object property`() {
        val givenProperty = TestClassWithMap::value

        val expectedObjectProperty = ObjectProperty(
            name = "value",
            description = null,
            nullable = false
        )

        assertEquals(expectedObjectProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Map property to object property`() {
        val givenProperty = TestClassWithNullableMap::value

        val expectedObjectProperty = ObjectProperty(
            name = "value",
            description = null,
            nullable = true
        )

        assertEquals(expectedObjectProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to object property with custom name`() {
        val givenProperty = TestClassWithCustomSerialName::value

        val expectedObjectProperty = ObjectProperty(
            name = "custom-value",
            description = null,
            nullable = false
        )

        assertEquals(expectedObjectProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to object property with description`() {
        val givenProperty = TestClassWithDescription::value

        val expectedObjectProperty = ObjectProperty(
            name = "value",
            description = "Description",
            nullable = false
        )

        assertEquals(expectedObjectProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should throw exception for unsupported object type`() {
        val givenProperty = TestClassWithInvalidType::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithMap(val value: Map<String, Any>)

    private data class TestClassWithNullableMap(val value: Map<String, Any>?)

    private data class TestClassWithCustomSerialName(@SerialName("custom-value") val value: Map<String, Any>)

    private data class TestClassWithDescription(@Description("Description") val value: Map<String, Any>)

    private data class TestClassWithInvalidType(val value: List<Any>)
}
