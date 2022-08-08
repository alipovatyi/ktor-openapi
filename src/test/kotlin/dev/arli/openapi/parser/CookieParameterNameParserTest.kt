package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Cookie
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class CookieParameterNameParserTest {

    private val parser = CookieParameterNameParser()

    @Test
    fun `Should throw an exception if Cookie annotation is not found`() {
        val givenProperty = TestClassWithoutAnnotation::value

        assertFailsWith<IllegalArgumentException> {
            parser.parse(givenProperty)
        }
    }

    @Test
    fun `Should return property name if Cookie annotation value is empty`() {
        val givenProperty = TestClassWithEmptyName::value

        val expectedName = "value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    @Test
    fun `Should return name from Cookie annotation`() {
        val givenProperty = TestClassWithCustomName::value

        val expectedName = "custom-value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    private data class TestClassWithoutAnnotation(val value: String)

    private data class TestClassWithEmptyName(@Cookie val value: String)

    private data class TestClassWithCustomName(@Cookie("custom-value") val value: String)
}
