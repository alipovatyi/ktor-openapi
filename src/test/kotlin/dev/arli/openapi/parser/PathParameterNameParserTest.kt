package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Path
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class PathParameterNameParserTest {

    private val parser = PathParameterNameParser()

    @Test
    fun `Should return property name if Path annotation is not found`() {
        val givenProperty = TestClassWithoutPath::value

        val expectedName = "value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    @Test
    fun `Should return property name if Path annotation value is empty`() {
        val givenProperty = TestClassWithEmptyPathValue::value

        val expectedName = "value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    @Test
    fun `Should return value from Path annotation`() {
        val givenProperty = TestClassWithCustomPathValue::value

        val expectedName = "custom-value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    private data class TestClassWithoutPath(val value: String)

    private data class TestClassWithEmptyPathValue(@Path val value: String)

    private data class TestClassWithCustomPathValue(@Path("custom-value") val value: String)
}
