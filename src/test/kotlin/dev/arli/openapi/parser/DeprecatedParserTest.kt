package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Deprecated
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

internal class DeprecatedParserTest {

    private val parser = DeprecatedParser()

    @Test
    fun `Should return true if annotation is found`() {
        val obj = object {
            @Deprecated
            val field: String = ""
        }

        assertTrue(parser.parse(obj::field))
    }

    @Test
    fun `Should return false if annotation is not found`() {
        val obj = object {
            val field: String = ""
        }

        assertFalse(parser.parse(obj::field))
    }
}
