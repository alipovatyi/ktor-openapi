package dev.arli.openapi.util

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class StringExtensionTest {

    @ParameterizedTest
    @MethodSource
    fun `Should remove trailing slash when needed`(given: String, expected: String) {
        assertThat(given.removeTrailingSlash()).isEqualTo(expected)
    }

    private companion object {

        @JvmStatic
        fun `Should remove trailing slash when needed`() = listOf(
            arguments("", ""),
            arguments("/", "/"),
            arguments("//", "/"),
            arguments("/root", "/root"),
            arguments("/root/", "/root"),
            arguments("/root/path", "/root/path"),
            arguments("/root/path/", "/root/path")
        )
    }
}
