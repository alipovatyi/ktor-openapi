package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

internal class PathParameterMapperTest {

    private val mapper = PathParameterMapper()

    @Test
    fun `Should map path parameter with default values to parameter object`() {
        val givenProperty = TestClassWithDefaultValues::value

        val expectedParameterObject = ParameterObject(
            name = "value",
            `in` = ParameterLocation.PATH,
            required = true,
            description = "",
            deprecated = false,
            schema = SchemaObject(
                type = DataType.INTEGER,
                format = IntegerFormat.INT_64,
                nullable = false,
                description = null,
                properties = emptyMap(),
                enum = emptySet()
            )
        )

        assertEquals(expectedParameterObject, mapper.map(givenProperty))
    }

    @Test
    fun `Should map path parameter with custom values to parameter object with custom values`() {
        val givenProperty = TestClassWithCustomValues::value

        val expectedParameterObject = ParameterObject(
            name = "custom-value",
            `in` = ParameterLocation.PATH,
            required = true,
            description = "Description",
            deprecated = true,
            schema = SchemaObject(
                type = DataType.INTEGER,
                format = IntegerFormat.INT_64,
                nullable = false,
                description = null,
                properties = emptyMap(),
                enum = emptySet()
            )
        )

        assertEquals(expectedParameterObject, mapper.map(givenProperty))
    }

    @Test
    fun `Should throw exception if Path annotation is missing`() {
        val givenProperty = TestClassWithoutAnnotation::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    @Test
    fun `Should throw exception if property is nullable`() {
        val givenProperty = TestClassWithNullableProperty::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithDefaultValues(@Path val value: Long)

    private data class TestClassWithCustomValues(
        @Path(
            name = "custom-value",
            description = "Description",
            deprecated = true
        )
        val value: Long
    )

    private data class TestClassWithoutAnnotation(val value: Int)

    private data class TestClassWithNullableProperty(val value: String?)
}
