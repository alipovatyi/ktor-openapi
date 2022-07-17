package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.Example.Companion.example
import dev.arli.openapi.model.Response.Companion.defaultResponse
import dev.arli.openapi.model.Response.Companion.response
import io.ktor.http.HttpStatusCode
import org.junit.jupiter.api.Test

internal class ResponseTest {

    @Test
    fun `Should create response with single example`() {
        val expectedResponse = Response(
            statusCode = HttpStatusCode.Created,
            example = "Example",
            examples = Examples(),
            responseClass = TestResponse::class
        )

        val actualResponse = response<TestResponse>(HttpStatusCode.Created) {
            example = "Example"
        }

        assertThat(actualResponse).isEqualTo(expectedResponse)
    }

    @Test
    fun `Should create response with multiple examples`() {
        val expectedResponse = Response(
            statusCode = HttpStatusCode.Created,
            example = null,
            examples = Examples(
                examples = mapOf(
                    "example-1" to example("Example 1"),
                    "example-2" to example("Example 2")
                )
            ),
            responseClass = TestResponse::class
        )

        val actualResponse = response<TestResponse>(HttpStatusCode.Created) {
            examples {
                example("example-1", value = "Example 1")
                example("example-2", value = "Example 2")
            }
        }

        assertThat(actualResponse).isEqualTo(expectedResponse)
    }

    @Test
    fun `Should create response with default values`() {
        val expectedResponse = Response(
            statusCode = HttpStatusCode.Created,
            example = null,
            examples = Examples(),
            responseClass = TestResponse::class
        )

        val actualResponse = response<TestResponse>(HttpStatusCode.Created)

        assertThat(actualResponse).isEqualTo(expectedResponse)
    }

    @Test
    fun `Should create default response with no status code and single example`() {
        val expectedResponse = Response(
            statusCode = null,
            example = "Example",
            examples = Examples(),
            responseClass = TestResponse::class
        )

        val actualResponse = defaultResponse<TestResponse> {
            example = "Example"
        }

        assertThat(actualResponse).isEqualTo(expectedResponse)
    }

    @Test
    fun `Should create default response with no status code and multiple examples`() {
        val expectedResponse = Response(
            statusCode = null,
            example = null,
            examples = Examples(
                examples = mapOf(
                    "example-1" to example(value = "Example 1"),
                    "example-2" to example(value = "Example 2")
                )
            ),
            responseClass = TestResponse::class
        )

        val actualResponse = defaultResponse<TestResponse> {
            examples {
                example("example-1", value = "Example 1")
                example("example-2", value = "Example 2")
            }
        }

        assertThat(actualResponse).isEqualTo(expectedResponse)
    }

    private object TestResponse
}
