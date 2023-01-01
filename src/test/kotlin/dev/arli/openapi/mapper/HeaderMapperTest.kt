package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Header
import dev.arli.openapi.model.HeaderObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class HeaderMapperTest {

    private val mapper = HeaderMapper()

    @Test
    fun `Should map header with default values to header object`() {
        val givenProperty = TestClassWithDefaultValues::value

        val expectedHeaderObject = HeaderObject(
            required = false,
            description = "",
            deprecated = false,
            schema = SchemaObject(
                type = DataType.STRING,
                format = StringFormat.NO_FORMAT,
                nullable = false,
                description = null,
                properties = emptyMap(),
                enum = emptySet()
            )
        )

        assertEquals(expectedHeaderObject, mapper.map(givenProperty))
    }

    @Test
    fun `Should map header with custom values to header object`() {
        val givenProperty = TestClassWithCustomValues::value

        val expectedHeaderObject = HeaderObject(
            required = true,
            description = "Description",
            deprecated = true,
            schema = SchemaObject(
                type = DataType.STRING,
                format = StringFormat.NO_FORMAT,
                nullable = false,
                description = null,
                properties = emptyMap(),
                enum = emptySet()
            )
        )

        assertEquals(expectedHeaderObject, mapper.map(givenProperty))
    }

    @Test
    fun `Should throw exception if Header annotation is missing`() {
        val givenProperty = TestClassWithoutAnnotation::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithDefaultValues(@Header val value: String)

    private data class TestClassWithCustomValues(
        @Header(name = "custom-value", description = "Description", required = true, deprecated = true)
        val value: String
    )

    private data class TestClassWithoutAnnotation(val value: Long)
}
