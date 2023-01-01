package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.property.StringFormat
import dev.arli.openapi.model.property.StringProperty
import kotlinx.serialization.SerialName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import java.time.LocalDate as JavaLocalDate
import java.time.LocalDateTime as JavaLocalDateTime
import java.time.LocalTime as JavaLocalTime
import java.time.OffsetDateTime as JavaOffsetDateTime
import kotlinx.datetime.LocalDate as KotlinLocalDate
import kotlinx.datetime.LocalDateTime as KotlinLocalDateTime

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
    fun `Should map Java LocalDate property to string property`() {
        val givenProperty = TestClassWithJavaLocalDate::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = false,
            format = StringFormat.DATE
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Java LocalDate property to nullable string property`() {
        val givenProperty = TestClassWithNullableJavaLocalDate::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = true,
            format = StringFormat.DATE
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map Java LocalTime property to string property`() {
        val givenProperty = TestClassWithJavaLocalTime::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = false,
            format = StringFormat.DATE_TIME
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Java LocalTime property to nullable string property`() {
        val givenProperty = TestClassWithNullableJavaLocalTime::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = true,
            format = StringFormat.DATE_TIME
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map Java LocalDateTime property to string property`() {
        val givenProperty = TestClassWithJavaLocalDateTime::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = false,
            format = StringFormat.DATE_TIME
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Java LocalDateTime property to nullable string property`() {
        val givenProperty = TestClassWithNullableJavaLocalDateTime::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = true,
            format = StringFormat.DATE_TIME
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map Java OffsetDateTime property to string property`() {
        val givenProperty = TestClassWithJavaOffsetDateTime::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = false,
            format = StringFormat.DATE_TIME
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Java OffsetDateTime property to nullable string property`() {
        val givenProperty = TestClassWithNullableJavaOffsetDateTime::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = true,
            format = StringFormat.DATE_TIME
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map Kotlin LocalDate property to string property`() {
        val givenProperty = TestClassWithKotlinLocalDate::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = false,
            format = StringFormat.DATE
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Kotlin LocalDate property to nullable string property`() {
        val givenProperty = TestClassWithNullableKotlinLocalDate::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = true,
            format = StringFormat.DATE
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map Kotlin LocalDateTime property to string property`() {
        val givenProperty = TestClassWithKotlinLocalDateTime::value

        val expectedStringProperty = StringProperty(
            name = "value",
            description = null,
            nullable = false,
            format = StringFormat.DATE_TIME
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Kotlin LocalDateTime to nullable string property`() {
        val givenProperty = TestClassWithNullableKotlinLocalDateTime::value

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

    private data class TestClassWithJavaLocalDate(val value: JavaLocalDate)

    private data class TestClassWithNullableJavaLocalDate(val value: JavaLocalDate?)

    private data class TestClassWithJavaLocalTime(val value: JavaLocalTime)

    private data class TestClassWithNullableJavaLocalTime(val value: JavaLocalTime?)

    private data class TestClassWithJavaLocalDateTime(val value: JavaLocalDateTime)

    private data class TestClassWithNullableJavaLocalDateTime(val value: JavaLocalDateTime?)

    private data class TestClassWithJavaOffsetDateTime(val value: JavaOffsetDateTime)

    private data class TestClassWithNullableJavaOffsetDateTime(val value: JavaOffsetDateTime?)

    private data class TestClassWithKotlinLocalDate(val value: KotlinLocalDate)

    private data class TestClassWithNullableKotlinLocalDate(val value: KotlinLocalDate?)

    private data class TestClassWithKotlinLocalDateTime(val value: KotlinLocalDateTime)

    private data class TestClassWithNullableKotlinLocalDateTime(val value: KotlinLocalDateTime?)

    private data class TestClassWithCustomSerialName(@SerialName("custom-value") val value: String)

    private data class TestClassWithDescription(@Description("Description") val value: String)

    private data class TestClassWithInvalidType(val value: Int)
}
