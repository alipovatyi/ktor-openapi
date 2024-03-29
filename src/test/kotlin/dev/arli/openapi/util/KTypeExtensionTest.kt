package dev.arli.openapi.util

import org.junit.jupiter.api.Test
import kotlin.reflect.full.createType
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class KTypeExtensionTest {

    @Test
    fun `isEnum should return false if type is not enum`() {
        assertFalse(String::class.createType().isEnum)
    }

    @Test
    fun `isEnum should return true if type is enum`() {
        assertTrue(TestEnum::class.createType().isEnum)
    }

    private enum class TestEnum
}
