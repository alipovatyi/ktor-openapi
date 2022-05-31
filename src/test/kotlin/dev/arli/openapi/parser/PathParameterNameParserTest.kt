package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Path
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

internal class PathParameterNameParserTest {

    private val parser = PathParameterNameParser()

    @Test
    fun `Should throw an exception if Path annotation is not found`() {
        val givenProperty = TestClassWithoutAnnotation::value

        assertFailsWith<IllegalArgumentException> {
            parser.parse(givenProperty)
        }
    }

    @Test
    fun `Should return property name if Path annotation value is empty`() {
        val givenProperty = TestClassWithEmptyName::value

        val expectedName = "value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    @Test
    fun `Should return name from Path annotation`() {
        val givenProperty = TestClassWithCustomName::value

        val expectedName = "custom-value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    private data class TestClassWithoutAnnotation(val value: String)

    private data class TestClassWithEmptyName(@Path val value: String)

    private data class TestClassWithCustomName(@Path("custom-value") val value: String)
}
