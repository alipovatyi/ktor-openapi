package dev.arli.openapi.parser

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PathParametersParserTest {

    private val parser = PathParametersParser()

    @Test
    fun `Should return path parameter name set`() {
        val givenPath = "/v1/{param-1}/details/{param-2}"
        val expectedPathParameterNameSet = setOf("param-1", "param-2")

        assertEquals(expectedPathParameterNameSet, parser.parse(givenPath))
    }

    @Test
    fun `Should return empty path parameter name set if no path parameters are found`() {
        val givenPath = "/v1/details"
        val expectedPathParameterNameSet = emptySet<String>()

        assertEquals(expectedPathParameterNameSet, parser.parse(givenPath))
    }
}
