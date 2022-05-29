package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.StringFormat
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

internal class PathParametersMapperTest {

    private val mapper = PathParametersMapper()

    @Test
    fun `Should map path parameters with default names to parameter components`() {
        val givenPathParameters = setOf("param1", "param2")
        val givenAnnotatedProperties = listOf(
            TestClassWithDefaultNames::param1,
            TestClassWithDefaultNames::param2
        )

        val expectedParameterComponents = listOf(
            ParameterObject(
                name = "param1",
                `in` = ParameterLocation.PATH,
                description = "",
                required = true,
                deprecated = false,
                schema = SchemaObject(
                    type = DataType.INTEGER,
                    format = IntegerFormat.INT_32,
                    nullable = false,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                )
            ),
            ParameterObject(
                name = "param2",
                `in` = ParameterLocation.PATH,
                description = "",
                required = true,
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
        )

        assertEquals(expectedParameterComponents, mapper.map(givenPathParameters, givenAnnotatedProperties))
    }

    @Test
    fun `Should map path parameters with custom names to parameter components`() {
        val givenPathParameters = setOf("custom-param-1", "param2", "custom-param-3")
        val givenAnnotatedProperties = listOf(
            TestClassWithCustomNames::param1,
            TestClassWithCustomNames::param2,
            TestClassWithCustomNames::param3
        )

        val expectedParameterComponents = listOf(
            ParameterObject(
                name = "custom-param-1",
                `in` = ParameterLocation.PATH,
                description = "",
                required = true,
                deprecated = false,
                schema = SchemaObject(
                    type = DataType.INTEGER,
                    format = IntegerFormat.INT_32,
                    nullable = false,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                )
            ),
            ParameterObject(
                name = "param2",
                `in` = ParameterLocation.PATH,
                description = "",
                required = true,
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
                `in` = ParameterLocation.PATH,
                description = "",
                required = true,
                deprecated = false,
                schema = SchemaObject(
                    type = DataType.BOOLEAN,
                    format = null,
                    nullable = false,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                )
            )
        )

        assertEquals(expectedParameterComponents, mapper.map(givenPathParameters, givenAnnotatedProperties))
    }

    @Test
    fun `Should throw an exception if Path annotation is missing for any of path parameters`() {
        val givenPathParameters = setOf("param-1", "param-2")
        val givenAnnotatedProperties = listOf(
            TestClassWithoutAnnotation::param1,
            TestClassWithoutAnnotation::param2
        )

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenPathParameters, givenAnnotatedProperties)
        }
    }

    private data class TestClassWithDefaultNames(@Path val param1: Int, @Path val param2: String)

    private data class TestClassWithCustomNames(
        @Path("custom-param-1")
        val param1: Int,
        @Path
        val param2: String,
        @Path("custom-param-3")
        val param3: Boolean
    )

    private data class TestClassWithoutAnnotation(@Path val param1: String, val param2: Long)
}
