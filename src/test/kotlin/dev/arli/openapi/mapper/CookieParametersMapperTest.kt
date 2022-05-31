package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Cookie
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.StringFormat
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

internal class CookieParametersMapperTest {

    private val mapper = CookieParametersMapper()

    @Test
    fun `Should map cookie parameters with default names to parameter components`() {
        val givenProperties = listOf(
            TestClassWithDefaultNames::param1,
            TestClassWithDefaultNames::param2
        )

        val expectedParameterComponents = listOf(
            ParameterObject(
                name = "param1",
                `in` = ParameterLocation.COOKIE,
                description = "",
                required = false,
                deprecated = false,
                schema = SchemaObject(
                    type = DataType.STRING,
                    format = StringFormat.NO_FORMAT,
                    nullable = false,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                )
            ),
            ParameterObject(
                name = "param2",
                `in` = ParameterLocation.COOKIE,
                description = "",
                required = false,
                deprecated = false,
                schema = SchemaObject(
                    type = DataType.INTEGER,
                    format = IntegerFormat.INT_32,
                    nullable = false,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                )
            )
        )

        assertEquals(expectedParameterComponents, mapper.map(givenProperties))
    }

    @Test
    fun `Should map cookie parameters with custom names to parameter components`() {
        val givenProperties = listOf(
            TestClassWithCustomNames::param1,
            TestClassWithCustomNames::param2,
            TestClassWithCustomNames::param3
        )

        val expectedParameterComponents = listOf(
            ParameterObject(
                name = "custom-param-1",
                `in` = ParameterLocation.COOKIE,
                description = "",
                required = false,
                deprecated = false,
                schema = SchemaObject(
                    type = DataType.BOOLEAN,
                    format = null,
                    nullable = false,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                )
            ),
            ParameterObject(
                name = "param2",
                `in` = ParameterLocation.COOKIE,
                description = "",
                required = false,
                deprecated = false,
                schema = SchemaObject(
                    type = DataType.STRING,
                    format = StringFormat.NO_FORMAT,
                    nullable = false,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                )
            ),
            ParameterObject(
                name = "custom-param-3",
                `in` = ParameterLocation.COOKIE,
                description = "",
                required = false,
                deprecated = false,
                schema = SchemaObject(
                    type = DataType.INTEGER,
                    format = IntegerFormat.INT_32,
                    nullable = false,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                )
            )
        )

        assertEquals(expectedParameterComponents, mapper.map(givenProperties))
    }

    @Test
    fun `Should throw an exception if Cookie parameter names are not unique`() {
        val givenProperties = listOf(
            TestClassWithNonUniqueNames::param1,
            TestClassWithNonUniqueNames::param2
        )

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperties)
        }
    }

    private data class TestClassWithDefaultNames(@Cookie val param1: String, @Cookie val param2: Int)

    private data class TestClassWithCustomNames(
        @Cookie("custom-param-1")
        val param1: Boolean,
        @Cookie
        val param2: String,
        @Cookie("custom-param-3")
        val param3: Int
    )

    private data class TestClassWithNonUniqueNames(
        @Cookie(name = "param")
        val param1: String,
        @Cookie(name = "param")
        val param2: Long
    )
}
