package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Header
import dev.arli.openapi.model.HeaderObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.StringFormat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class HeadersMapperTest {

    private val mapper = HeadersMapper()

    @Test
    fun `Should map headers with default names to header components`() {
        val givenProperties = listOf(
            TestClassWithDefaultNames::param1,
            TestClassWithDefaultNames::param2
        )

        val expectedHeaderComponents = mapOf(
            "param1" to HeaderObject(
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
            "param2" to HeaderObject(
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

        assertEquals(expectedHeaderComponents, mapper.map(givenProperties))
    }

    @Test
    fun `Should map headers with custom names to header components`() {
        val givenProperties = listOf(
            TestClassWithCustomNames::param1,
            TestClassWithCustomNames::param2,
            TestClassWithCustomNames::param3
        )

        val expectedHeaderComponents = mapOf(
            "custom-param-1" to HeaderObject(
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
            "param2" to HeaderObject(
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
            "custom-param-3" to HeaderObject(
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

        assertEquals(expectedHeaderComponents, mapper.map(givenProperties))
    }

    @Test
    fun `Should ignore Content-Type header`() {
        val givenProperties = listOf(
            TestClassWithContentType::param1,
            TestClassWithContentType::param2
        )

        val expectedHeaderComponents = mapOf(
            "param1" to HeaderObject(
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
            )
        )

        assertEquals(expectedHeaderComponents, mapper.map(givenProperties))
    }

    @Test
    fun `Should throw an exception if header names are not unique`() {
        val givenProperties = listOf(
            TestClassWithNonUniqueNames::param1,
            TestClassWithNonUniqueNames::param2
        )

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperties)
        }
    }

    private data class TestClassWithDefaultNames(@Header val param1: String, @Header val param2: Int)

    private data class TestClassWithCustomNames(
        @Header("custom-param-1") val param1: Boolean,
        @Header val param2: String,
        @Header("custom-param-3") val param3: Int
    )

    private data class TestClassWithContentType(
        @Header val param1: String,
        @Header("Content-Type") val param2: String
    )

    private data class TestClassWithNonUniqueNames(
        @Header(name = "param") val param1: String,
        @Header(name = "param") val param2: Long
    )
}
