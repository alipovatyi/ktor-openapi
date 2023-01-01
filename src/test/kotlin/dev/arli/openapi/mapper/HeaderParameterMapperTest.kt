package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Header
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class HeaderParameterMapperTest {

    private val mapper = HeaderParameterMapper()

    @Test
    fun `Should map header parameter with default values to parameter object`() {
        val givenProperty = TestClassWithDefaultValues::value

        val expectedParameterObject = ParameterObject(
            name = "value",
            `in` = ParameterLocation.HEADER,
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

        assertEquals(expectedParameterObject, mapper.map(givenProperty))
    }

    @Test
    fun `Should map header parameter with custom values to parameter object`() {
        val givenProperty = TestClassWithCustomValues::value

        val expectedParameterObject = ParameterObject(
            name = "custom-value",
            `in` = ParameterLocation.HEADER,
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

        assertEquals(expectedParameterObject, mapper.map(givenProperty))
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
