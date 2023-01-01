package dev.arli.openapi.parser

import kotlinx.serialization.SerialName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PropertyNameParserTest {

    private val parser = PropertyNameParser()

    @Test
    fun `Should return property name if SerialName annotation is not found`() {
        val givenProperty = TestClass::value

        val expectedName = "value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    @Test
    fun `Should return value from SerialName annotation`() {
        val givenProperty = TestClassWithCustomSerialName::value

        val expectedName = "custom-value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    private data class TestClass(val value: String)

    private data class TestClassWithCustomSerialName(@SerialName("custom-value") val value: String)
}
