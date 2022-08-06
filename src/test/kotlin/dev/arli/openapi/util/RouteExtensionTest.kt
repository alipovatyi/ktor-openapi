package dev.arli.openapi.util

import com.google.common.truth.Truth.assertThat
import io.ktor.server.routing.Route
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class RouteExtensionTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return full route path`(givenRoute: Route?, expected: String) {
        assertThat(givenRoute.getPath()).isEqualTo(expected)
    }

    private companion object {

        @JvmStatic
        fun `Should return full route path`() = listOf<Arguments>(
            arguments(null, "/"),
            // TODO: add more cases
        )
    }
}
