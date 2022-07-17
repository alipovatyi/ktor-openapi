package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.Responses.Companion.responses
import io.ktor.http.HttpStatusCode
import org.junit.jupiter.api.Test

internal class ResponsesTest {

    @Test
    fun `Should create responses`() {
        val expectedResponses = Responses(
            responses = listOf(
                Response(
                    responseClass = String::class,
                    statusCode = null,
                    example = "Example",
                    examples = Examples(emptyMap())
                ),
                Response(
                    responseClass = Int::class,
                    statusCode = HttpStatusCode.OK,
                    example = null,
                    examples = Examples(
                        examples = mapOf(
                            "value-1" to Example(
                                value = 1,
                                summary = "Summary",
                                description = null
                            ),
                            "value-2" to Example(
                                value = 2,
                                summary = null,
                                description = "Description"
                            )
                        )
                    )
                )
            )
        )

        val actualResponses = responses {
            defaultResponse<String> {
                example = "Example"
            }
            response<Int>(HttpStatusCode.OK) {
                examples {
                    example(name = "value-1", value = 1) {
                        summary = "Summary"
                    }
                    example(name = "value-2", value = 2) {
                        description = "Description"
                    }
                }
            }
        }

        assertThat(actualResponses).isEqualTo(expectedResponses)
    }
}
