package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Cookie
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

internal class CookieParameterMapperTest {

    private val mapper = CookieParameterMapper()

    @Test
    fun `Should map cookie parameter with default values to parameter object`() {
        val givenProperty = TestClassWithDefaultValues::value

        val expectedParameterObject = ParameterObject(
            name = "value",
            `in` = ParameterLocation.COOKIE,
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
    fun `Should map cookie parameter with custom values to parameter object`() {
        val givenProperty = TestClassWithCustomValues::value

        val expectedParameterObject = ParameterObject(
            name = "custom-value",
            `in` = ParameterLocation.COOKIE,
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
    fun `Should throw exception if Cookie annotation is missing`() {
        val givenProperty = TestClassWithoutAnnotation::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithDefaultValues(@Cookie val value: String)

    private data class TestClassWithCustomValues(
        @Cookie(name = "custom-value", description = "Description", required = true, deprecated = true)
        val value: String
    )

    private data class TestClassWithoutAnnotation(val value: Long)
}
