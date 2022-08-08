package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.property.StringFormat
import dev.arli.openapi.model.property.StringProperty
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class StringPropertyMapperTest {

    private val mapper = StringPropertyMapper()

    @Test
    fun `Should map String property to string property`() {
        val givenProperty = TestClassWithString::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = false,
            format = StringFormat.NO_FORMAT
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable String property to nullable string property`() {
        val givenProperty = TestClassWithNullableString::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = true,
            format = StringFormat.NO_FORMAT
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map LocalDate property to string property`() {
        val givenProperty = TestClassWithLocalDate::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = false,
            format = StringFormat.DATE
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable LocalDate property to nullable string property`() {
        val givenProperty = TestClassWithNullableLocalDate::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = true,
            format = StringFormat.DATE
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map LocalDateTime property to string property`() {
        val givenProperty = TestClassWithLocalDateTime::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = false,
            format = StringFormat.DATE_TIME
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable LocalDateTime to nullable string property`() {
        val givenProperty = TestClassWithNullableLocalDateTime::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = true,
            format = StringFormat.DATE_TIME
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to string property with custom name`() {
        val givenProperty = TestClassWithCustomSerialName::value

        val expectedStringProperty = StringProperty(
            name = "custom-value",
            description = null,
            nullable = false,
            format = StringFormat.NO_FORMAT
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to string property with description`() {
        val givenProperty = TestClassWithDescription::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = "Description",
            nullable = false,
            format = StringFormat.NO_FORMAT
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should throw exception for unsupported string type`() {
        val givenProperty = TestClassWithInvalidType::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithString(val value: String)

    private data class TestClassWithNullableString(val value: String?)

    private data class TestClassWithLocalDate(val value: LocalDate)

    private data class TestClassWithNullableLocalDate(val value: LocalDate?)

    private data class TestClassWithLocalDateTime(val value: LocalDateTime)

    private data class TestClassWithNullableLocalDateTime(val value: LocalDateTime?)

    private data class TestClassWithCustomSerialName(@SerialName("custom-value") val value: String)

    private data class TestClassWithDescription(@Description("Description") val value: String)

    private data class TestClassWithInvalidType(val value: Int)
}
