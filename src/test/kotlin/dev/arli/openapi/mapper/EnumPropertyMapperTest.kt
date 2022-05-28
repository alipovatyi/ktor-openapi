package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.property.EnumProperty
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Test

internal class EnumPropertyMapperTest {

    private val mapper = EnumPropertyMapper()

    @Test
    fun `Should map Enum property to enum property`() {
        val givenProperty = TestClassWithEnum::value

        val expectedEnumProperty = EnumProperty(
            name = "value",
            description = null,
            nullable = false,
            values = setOf("value-1", "VALUE_2")
        )

        assertEquals(expectedEnumProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Enum property to nullable enum property`() {
        val givenProperty = TestClassWithNullableEnum::value

        val expectedEnumProperty = EnumProperty(
            name = "value",
            description = null,
            nullable = true,
            values = setOf("value-1", "VALUE_2")
        )

        assertEquals(expectedEnumProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to enum property with custom name`() {
        val givenProperty = TestClassWithCustomSerialName::value

        val expectedEnumProperty = EnumProperty(
            name = "custom-value",
            description = null,
            nullable = false,
            values = setOf("value-1", "VALUE_2")
        )

        assertEquals(expectedEnumProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to enum property with description`() {
        val givenProperty = TestClassWithDescription::value

        val expectedEnumProperty = EnumProperty(
            name = "value",
            description = "Description",
            nullable = false,
            values = setOf("value-1", "VALUE_2")
        )

        assertEquals(expectedEnumProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should throw exception for unsupported enum type`() {
        val givenProperty = TestClassWithInvalidType::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithEnum(val value: TestEnum)

    private data class TestClassWithNullableEnum(val value: TestEnum?)

    private data class TestClassWithCustomSerialName(@SerialName("custom-value") val value: TestEnum)

    private data class TestClassWithDescription(@Description("Description") val value: TestEnum)

    private data class TestClassWithInvalidType(val value: String)

    @Serializable
    private enum class TestEnum {
        @SerialName("value-1") VALUE_1,
        VALUE_2
    }
}
