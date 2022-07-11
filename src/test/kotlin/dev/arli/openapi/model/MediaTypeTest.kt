package dev.arli.openapi.model

import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MediaTypeTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return correct key for data type`(givenMediaType: MediaType, expectedKey: String) {
        assertEquals(expectedKey, givenMediaType.key)
    }

    private companion object {

        @JvmStatic
        fun `Should return correct key for data type`() = listOf(
            arguments(MediaType.APPLICATION_JSON, "application/json"),
            arguments(MediaType.APPLICATION_XML, "application/xml"),
            arguments(MediaType.APPLICATION_FORM, "application/x-www-form-urlencoded"),
            arguments(MediaType.APPLICATION_PDF, "application/pdf"),
            arguments(MediaType.MULTIPART_FORM, "multipart/form-data"),
            arguments(MediaType.IMG_PNG, "image/png"),
            arguments(MediaType.TEXT_PLAIN, "text/plain"),
            arguments(MediaType.TEXT_HTML, "text/html")
        )
    }
}
