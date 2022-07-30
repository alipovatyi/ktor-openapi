package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.annotation.Description
import dev.arli.openapi.annotation.RequestBody
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.RequestBodyObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import kotlin.reflect.KProperty
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class RequestBodyMapperTest {

    private val mapper = RequestBodyMapper()

    @Test
    fun `Should map request body with default values to request body object`() {
        val givenProperty = TestClassWithDefaultValues::requestBody

        val expectedRequestBodyObject = RequestBodyObject<String>(
            description = null,
            content = mapOf(
                MediaType.APPLICATION_JSON to MediaTypeObject(
                    schema = SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = false
                    )
                )
            ),
            required = false
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedRequestBodyObject)
    }

    @Test
    fun `Should map request body to request body object with custom values`() {
        val givenProperty = TestClassWithCustomValues::requestBody

        val expectedRequestBodyObject = RequestBodyObject<String>(
            description = null,
            content = mapOf(
                MediaType.APPLICATION_JSON to MediaTypeObject(
                    schema = SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = false
                    )
                )
            ),
            required = true
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedRequestBodyObject)
    }

    @Test
    fun `Should map request body class to request body object with description`() {
        val givenProperty = TestClassWithDescription::requestBody

        val expectedRequestBodyObject = RequestBodyObject<String>(
            description = "Description",
            content = mapOf(
                MediaType.APPLICATION_JSON to MediaTypeObject(
                    schema = SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = false,
                        description = "Description" // TODO: should be ignored in schema
                    )
                )
            ),
            required = false
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedRequestBodyObject)
    }

    @Test
    fun `Should throw an exception if RequestBody annotation is not found`() {
        val givenProperty = TestClassWithoutAnnotation::requestBody

        assertThrows<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    @Test
    fun `Should throw an exception if media type is not provided`() {
        val givenProperty = TestClassWithoutMediaType::requestBody

        assertThrows<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    @ParameterizedTest
    @MethodSource
    fun `Should throw an exception if media type is not supported`(givenProperty: KProperty<*>) {
        assertThrows<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithDefaultValues(@RequestBody val requestBody: String)
    private data class TestClassWithCustomValues(@RequestBody(required = true) val requestBody: String)

    private data class TestClassWithDescription(
        @RequestBody
        @Description("Description")
        val requestBody: String
    )

    private data class TestClassWithoutAnnotation(val requestBody: String)
    private data class TestClassWithoutMediaType(@RequestBody(mediaType = []) val requestBody: String)
    private data class TestClassWithApplicationXml(@RequestBody(MediaType.APPLICATION_XML) val requestBody: String)
    private data class TestClassWithApplicationForm(@RequestBody(MediaType.APPLICATION_FORM) val requestBody: String)
    private data class TestClassWithApplicationPdf(@RequestBody(MediaType.APPLICATION_PDF) val requestBody: String)
    private data class TestClassWithMultipartForm(@RequestBody(MediaType.APPLICATION_FORM) val requestBody: String)
    private data class TestClassWithImagePng(@RequestBody(MediaType.IMG_PNG) val requestBody: String)
    private data class TestClassWithTextPlain(@RequestBody(MediaType.TEXT_PLAIN) val requestBody: String)
    private data class TestClassWithTextHtml(@RequestBody(MediaType.TEXT_HTML) val requestBody: String)

    private companion object {

        @JvmStatic
        fun `Should throw an exception if media type is not supported`() = listOf(
            arguments(TestClassWithApplicationXml::requestBody),
            arguments(TestClassWithApplicationForm::requestBody),
            arguments(TestClassWithApplicationPdf::requestBody),
            arguments(TestClassWithMultipartForm::requestBody),
            arguments(TestClassWithImagePng::requestBody),
            arguments(TestClassWithTextPlain::requestBody),
            arguments(TestClassWithTextHtml::requestBody)
        )
    }
}
