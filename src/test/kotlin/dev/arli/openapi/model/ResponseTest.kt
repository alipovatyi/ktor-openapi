package dev.arli.openapi.model

import io.ktor.http.HttpStatusCode
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class ResponseTest {

    @Test
    fun `Should create response`() {
        val expectedResponse = Response(
            statusCode = HttpStatusCode.Created,
            example = TestResponseClass("String example 1"),
            examples = listOf(
                TestResponseClass("String example 2"),
                TestResponseClass("String example 3")
            ),
            responseClass = TestResponseClass::class
        )

        val actualResponse = response(
            statusCode = HttpStatusCode.Created,
            example = TestResponseClass("String example 1"),
            examples = listOf(
                TestResponseClass("String example 2"),
                TestResponseClass("String example 3")
            )
        )

        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `Should create default response with no status code`() {
        val expectedResponse = Response(
            statusCode = null,
            example = TestResponseClass("String example 1"),
            examples = listOf(
                TestResponseClass("String example 2"),
                TestResponseClass("String example 3")
            ),
            responseClass = TestResponseClass::class
        )

        val actualResponse = defaultResponse(
            example = TestResponseClass("String example 1"),
            examples = listOf(
                TestResponseClass("String example 2"),
                TestResponseClass("String example 3")
            )
        )

        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `Should create list of responses`() {
        val givenResponse1 = response<String>(statusCode = HttpStatusCode.OK)
        val givenResponse2 = response<TestResponseClass>(statusCode = HttpStatusCode.OK)

        val expectedResponses = listOf(givenResponse1, givenResponse2)

        val actualResponses = responses(givenResponse1, givenResponse2)

        assertEquals(expectedResponses, actualResponses)
    }

    private data class TestResponseClass(val value: String)
}
