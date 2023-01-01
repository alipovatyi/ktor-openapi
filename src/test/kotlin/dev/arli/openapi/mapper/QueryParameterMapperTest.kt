package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Query
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class QueryParameterMapperTest {

    private val mapper = QueryParameterMapper()

    @Test
    fun `Should map query parameter with default values to parameter object`() {
        val givenProperty = TestClassWithDefaultValues::value

        val expectedParameterObject = ParameterObject(
            name = "value",
            `in` = ParameterLocation.QUERY,
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
    fun `Should map query parameter with custom values to parameter object`() {
        val givenProperty = TestClassWithCustomValues::value

        val expectedParameterObject = ParameterObject(
            name = "custom-value",
            `in` = ParameterLocation.QUERY,
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
    fun `Should throw exception if Query annotation is missing`() {
        val givenProperty = TestClassWithoutAnnotation::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithDefaultValues(@Query val value: String)

    private data class TestClassWithCustomValues(
        @Query(name = "custom-value", description = "Description", required = true, deprecated = true)
        val value: String
    )

    private data class TestClassWithoutAnnotation(val value: Long)
}
