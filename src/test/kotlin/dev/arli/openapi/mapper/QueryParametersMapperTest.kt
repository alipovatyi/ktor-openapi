package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Query
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.StringFormat
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

internal class QueryParametersMapperTest {

    private val mapper = QueryParametersMapper()

    @Test
    fun `Should map query parameters with default names to parameter components`() {
        val givenProperties = listOf(
            TestClassWithDefaultNames::param1,
            TestClassWithDefaultNames::param2
        )

        val expectedParameterComponents = listOf(
            ParameterObject(
                name = "param1",
                `in` = ParameterLocation.QUERY,
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
                `in` = ParameterLocation.QUERY,
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
    fun `Should map query parameters with custom names to parameter components`() {
        val givenProperties = listOf(
            TestClassWithCustomNames::param1,
            TestClassWithCustomNames::param2,
            TestClassWithCustomNames::param3
        )

        val expectedParameterComponents = listOf(
            ParameterObject(
                name = "custom-param-1",
                `in` = ParameterLocation.QUERY,
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
                `in` = ParameterLocation.QUERY,
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
                `in` = ParameterLocation.QUERY,
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
    fun `Should throw an exception if Query parameter names are not unique`() {
        val givenProperties = listOf(
            TestClassWithNonUniqueNames::param1,
            TestClassWithNonUniqueNames::param2
        )

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperties)
        }
    }

    private data class TestClassWithDefaultNames(@Query val param1: String, @Query val param2: Int)

    private data class TestClassWithCustomNames(
        @Query("custom-param-1")
        val param1: Boolean,
        @Query
        val param2: String,
        @Query("custom-param-3")
        val param3: Int
    )

    private data class TestClassWithNonUniqueNames(
        @Query(name = "param")
        val param1: String,
        @Query(name = "param")
        val param2: Long
    )
}
