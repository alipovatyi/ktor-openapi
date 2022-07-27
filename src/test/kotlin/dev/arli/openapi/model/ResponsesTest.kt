package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import org.junit.jupiter.api.Test

internal class ResponsesTest {

    private val json = Json

    @Test
    fun `Should create responses`() {
        val expectedResponses = Responses(
            responses = listOf(
                Response(
                    responseClass = TestResponse::class,
                    statusCode = HttpStatusCode.OK,
                    example = null,
                    exampleJson = null,
                    examples = Examples(
                        examples = mapOf(
                            "value-1" to Example(
                                value = 1,
                                valueJson = JsonPrimitive(1),
                                summary = "Summary",
                                description = null
                            ),
                            "value-2" to Example(
                                value = 2,
                                valueJson = JsonPrimitive(2),
                                summary = null,
                                description = "Description"
                            )
                        )
                    )
                )
            )
        )

        val actualResponses = Responses.Builder(json = json).apply {
            response<TestResponse, Int>(HttpStatusCode.OK) {
                examples {
                    example(name = "value-1", value = 1) {
                        summary = "Summary"
                    }
                    example(name = "value-2", value = 2) {
                        description = "Description"
                    }
                }
            }
        }.build()

        assertThat(actualResponses).isEqualTo(expectedResponses)
    }

    @Test
    fun `Should create responses with default response`() {
        val expectedResponses = Responses(
            responses = listOf(
                Response(
                    responseClass = TestResponse::class,
                    statusCode = null,
                    example = "Example",
                    exampleJson = JsonPrimitive("Example"),
                    examples = Examples(emptyMap())
                )
            )
        )

        val actualResponses = Responses.Builder(json = json).apply {
            defaultResponse<TestResponse, String> {
                example = "Example"
            }
        }.build()

        assertThat(actualResponses).isEqualTo(expectedResponses)
    }

    private object TestResponse
}
