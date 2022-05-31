package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Header
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

internal class HeaderParameterNameParserTest {

    private val parser = HeaderParameterNameParser()

    @Test
    fun `Should throw an exception if Header annotation is not found`() {
        val givenProperty = TestClassWithoutAnnotation::value

        assertFailsWith<IllegalArgumentException> {
            parser.parse(givenProperty)
        }
    }

    @Test
    fun `Should return property name if Header annotation value is empty`() {
        val givenProperty = TestClassWithEmptyName::value

        val expectedName = "value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    @Test
    fun `Should return name from Header annotation`() {
        val givenProperty = TestClassWithCustomName::value

        val expectedName = "custom-value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    @Test
    fun `Should throw an exception if header name is Content-Type`() {
        val givenProperty = TestClassWithContentType::value

        assertFailsWith<IllegalArgumentException> {
            parser.parse(givenProperty)
        }
    }

    @Test
    fun `Should throw an exception if header name is Accept`() {
        val givenProperty = TestClassWithAccept::value

        assertFailsWith<IllegalArgumentException> {
            parser.parse(givenProperty)
        }
    }

    @Test
    fun `Should throw an exception if header name is Authorization`() {
        val givenProperty = TestClassWithAuthorization::value

        assertFailsWith<IllegalArgumentException> {
            parser.parse(givenProperty)
        }
    }

    private data class TestClassWithoutAnnotation(val value: String)

    private data class TestClassWithEmptyName(@Header val value: String)

    private data class TestClassWithCustomName(@Header("custom-value") val value: String)

    private data class TestClassWithContentType(@Header("Content-Type") val value: String)

    private data class TestClassWithAccept(@Header("Accept") val value: String)

    private data class TestClassWithAuthorization(@Header("Authorization") val value: String)
}
