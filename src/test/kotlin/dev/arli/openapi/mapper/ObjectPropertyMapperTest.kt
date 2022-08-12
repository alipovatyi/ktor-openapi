package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.property.ObjectProperty
import kotlinx.serialization.SerialName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class ObjectPropertyMapperTest {

    private val mapper = ObjectPropertyMapper()

    @Test
    fun `Should map Object property to object property`() {
        val givenProperty = TestClassWithObject::value

        val expectedObjectProperty = ObjectProperty(
            name = "value",
            description = null,
            nullable = false
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedObjectProperty)
    }

    @Test
    fun `Should map nullable Object property to object property`() {
        val givenProperty = TestClassWithNullableObject::value

        val expectedObjectProperty = ObjectProperty(
            name = "value",
            description = null,
            nullable = true
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedObjectProperty)
    }

    @Test
    fun `Should map property to object property with custom name`() {
        val givenProperty = TestClassWithCustomSerialName::value

        val expectedObjectProperty = ObjectProperty(
            name = "custom-value",
            description = null,
            nullable = false
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedObjectProperty)
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

    private data class TestClassWithObject(val value: TestObject)

    private data class TestClassWithNullableObject(val value: TestObject?)

    private data class TestClassWithCustomSerialName(@SerialName("custom-value") val value: TestObject)

    private data class TestClassWithDescription(@Description("Description") val value: TestObject)

    private data class TestClassWithInvalidType(val value: List<Any>)

    private data class TestObject(val property1: String, val property2: Int)
}
