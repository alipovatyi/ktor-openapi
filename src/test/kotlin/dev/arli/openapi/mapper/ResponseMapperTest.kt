package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Header
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.model.HeaderObject
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.Response.Companion.defaultResponse
import dev.arli.openapi.model.ResponseObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ResponseMapperTest {

    private val mapper = ResponseMapper()

    @Test
    fun `Should map response class to response object with headers`() {
        val givenResponse = defaultResponse<TestClassWithHeaders>()

        val expectedResponseObject = ResponseObject(
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
        val givenResponse = defaultResponse<TestClassWithContent>()

        val expectedMediaType1 = MediaTypeObject(
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
        val expectedMediaType2 = MediaTypeObject(
            schema = SchemaObject(
                type = DataType.STRING,
                format = StringFormat.NO_FORMAT,
                nullable = false
            )
        )
        val expectedResponseObject = ResponseObject(
            description = "Description",
            headers = emptyMap(),
            content = mapOf(
                MediaType.APPLICATION_JSON to expectedMediaType1,
                MediaType.APPLICATION_FORM to expectedMediaType2
            ),
            links = emptyMap()
        )

        assertThat(mapper.map(givenResponse)).isEqualTo(expectedResponseObject)
    }

    @Test
    fun `Should throw an exception if Response annotation is not found`() {
        val givenResponse = defaultResponse<TestClassWithoutHeader>()

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
        @Content val content1: TestContent,
        @Content(mediaType = MediaType.APPLICATION_FORM) val content2: String
    )

    private object TestClassWithoutHeader

    private data class TestContent(val value: String)
}
