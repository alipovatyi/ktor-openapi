package dev.arli.openapi.util

import kotlin.reflect.full.createType
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

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
