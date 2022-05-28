package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.ArrayProperty
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.serialization.SerialName
import org.junit.jupiter.api.Test

internal class ArrayPropertyMapperTest {

    private val mapper = ArrayPropertyMapper()

    @Test
    fun `Should map Array property to array property`() {
        val givenProperty = TestClassWithArray::value

        val expectedArrayProperty = ArrayProperty(
            name = "value",
            description = null,
            nullable = false
        )

        assertEquals(expectedArrayProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Array property to array property`() {
        val givenProperty = TestClassWithNullableArray::value

        val expectedArrayProperty = ArrayProperty(
            name = "value",
            description = null,
            nullable = true
        )

        assertEquals(expectedArrayProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map List property to array property`() {
        val givenProperty = TestClassWithList::value

        val expectedArrayProperty = ArrayProperty(
            name = "value",
            description = null,
            nullable = false
        )

        assertEquals(expectedArrayProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable List property to array property`() {
        val givenProperty = TestClassWithNullableList::value

        val expectedArrayProperty = ArrayProperty(
            name = "value",
            description = null,
            nullable = true
        )

        assertEquals(expectedArrayProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map Set property to array property`() {
        val givenProperty = TestClassWithSet::value

        val expectedArrayProperty = ArrayProperty(
            name = "value",
            description = null,
            nullable = false
        )

        assertEquals(expectedArrayProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Set property to array property`() {
        val givenProperty = TestClassWithNullableSet::value

        val expectedArrayProperty = ArrayProperty(
            name = "value",
            description = null,
            nullable = true
        )

        assertEquals(expectedArrayProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to array property with custom name`() {
        val givenProperty = TestClassWithCustomSerialName::value

        val expectedArrayProperty = ArrayProperty(
            name = "custom-value",
            description = null,
            nullable = false
        )

        assertEquals(expectedArrayProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to array property with description`() {
        val givenProperty = TestClassWithDescription::value

        val expectedArrayProperty = ArrayProperty(
            name = "value",
            description = "Description",
            nullable = false
        )

        assertEquals(expectedArrayProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should throw exception for unsupported array type`() {
        val givenProperty = TestClassWithInvalidType::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithArray(val value: Array<Any>)

    private data class TestClassWithNullableArray(val value: Array<Any>?)

    private data class TestClassWithList(val value: List<Any>)

    private data class TestClassWithNullableList(val value: List<Any>?)

    private data class TestClassWithSet(val value: Set<Any>)

    private data class TestClassWithNullableSet(val value: Set<Any>?)

    private data class TestClassWithCustomSerialName(@SerialName("custom-value") val value: List<Any>)

    private data class TestClassWithDescription(@Description("Description") val value: List<Any>)

    private data class TestClassWithInvalidType(val value: Map<Any, Any>)
}
