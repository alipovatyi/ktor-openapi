package dev.arli.openapi.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ParameterLocationTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return correct key for parameter location`(
        givenParameterLocation: ParameterLocation,
        expectedKey: String
    ) {
        assertEquals(expectedKey, givenParameterLocation.key)
    }

    private companion object {

        @JvmStatic
        fun `Should return correct key for parameter location`() = listOf(
            arguments(ParameterLocation.PATH, "path"),
            arguments(ParameterLocation.QUERY, "query"),
            arguments(ParameterLocation.HEADER, "header"),
            arguments(ParameterLocation.COOKIE, "cookie")
        )
    }
}
