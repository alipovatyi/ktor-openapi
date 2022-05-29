package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Query
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class QueryParameterNameParserTest {

    private val parser = QueryParameterNameParser()

    @Test
    fun `Should return property name if Query annotation is not found`() {
        val givenProperty = TestClassWithoutAnnotation::value

        val expectedName = "value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    @Test
    fun `Should return property name if Query annotation value is empty`() {
        val givenProperty = TestClassWithEmptyName::value

        val expectedName = "value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    @Test
    fun `Should return name from Query annotation`() {
        val givenProperty = TestClassWithCustomName::value

        val expectedName = "custom-value"

        assertEquals(expectedName, parser.parse(givenProperty))
    }

    private data class TestClassWithoutAnnotation(val value: String)

    private data class TestClassWithEmptyName(@Query val value: String)

    private data class TestClassWithCustomName(@Query("custom-value") val value: String)
}
