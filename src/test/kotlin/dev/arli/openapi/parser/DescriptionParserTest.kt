package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Description
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class DescriptionParserTest {

    private val parser = DescriptionParser()

    @Test
    fun `Should parse description from annotation`() {
        val obj = object {
            @Description("Test description")
            val field: String = ""
        }

        assertEquals("Test description", parser.parse(obj::field))
    }

    @Test
    fun `Should return null if annotation is not found`() {
        val obj = object {
            val field: String = ""
        }

        assertNull(parser.parse(obj::field))
    }
}
