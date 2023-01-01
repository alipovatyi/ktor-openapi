package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Header
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class HeaderNameParserTest {

    private val parser = HeaderNameParser()

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

    private data class TestClassWithoutAnnotation(val value: String)

    private data class TestClassWithEmptyName(@Header val value: String)

    private data class TestClassWithCustomName(@Header("custom-value") val value: String)
}
