package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Header
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.model.HeaderObject
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.ResponseObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import dev.arli.openapi.model.Response as ResponseModel

internal class ResponseMapperTest {

    private val json = Json
    private val mapper = ResponseMapper()

    @Test
    fun `Should map response class to response object with headers`() {
        val givenResponse = ResponseModel.Builder<TestClassWithHeaders, Unit>(
            json = json,
            responseClass = TestClassWithHeaders::class,
            statusCode = null
        ).build(exampleJson = null)

        val expectedResponseObject = ResponseObject<Unit>(
            description = "Description",
            headers = mapOf(
                "Authorization" to HeaderObject(
                    description = "",
                    schema = SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = false
                    )
                ),
                "Custom-Header" to HeaderObject(
                    description = "",
                    schema = SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = false
                    )
                )
            ),
            content = emptyMap(),
            links = emptyMap()
        )

        assertThat(mapper.map(givenResponse)).isEqualTo(expectedResponseObject)
    }

    @Test
    fun `Should map response class to response object with content`() {
        val givenResponse = ResponseModel.Builder<TestClassWithContent, TestContent>(
            json = json,
            responseClass = TestClassWithContent::class,
            statusCode = null
        ).build(exampleJson = null)

        val expectedMediaType = MediaTypeObject<TestContent>(
            schema = SchemaObject(
                type = DataType.OBJECT,
                format = null,
                nullable = false,
                properties = mapOf(
                    "value" to SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = false
                    )
                )
            )
        )
        val expectedResponseObject = ResponseObject(
            description = "Description",
            headers = emptyMap(),
            content = mapOf(MediaType.APPLICATION_JSON to expectedMediaType),
            links = emptyMap()
        )

        assertThat(mapper.map(givenResponse)).isEqualTo(expectedResponseObject)
    }

    @Test
    fun `Should throw an exception if Response annotation is not found`() {
        val givenResponse = ResponseModel.Builder<TestClassWithoutHeader, Unit>(
            json = json,
            responseClass = TestClassWithoutHeader::class,
            statusCode = null
        ).build(exampleJson = null)

        assertThrows<IllegalArgumentException> {
            mapper.map(givenResponse)
        }
    }

    @Response(description = "Description")
    private data class TestClassWithHeaders(
        @Header("Authorization") val header1: String,
        @Header("Custom-Header") val header2: String
    )

    @Response(description = "Description")
    private data class TestClassWithContent(
        @Content val content1: TestContent
    )

    private object TestClassWithoutHeader

    private data class TestContent(val value: String)
}
