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
                    mediaTypeExamples = MediaTypeExamples(
                        example = null,
                        exampleJson = null,
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
    fun `Should create responses with empty response`() {
        val expectedResponses = Responses(
            responses = listOf(
                Response(
                    responseClass = TestResponse::class,
                    statusCode = HttpStatusCode.OK,
                    mediaTypeExamples = MediaTypeExamples<Any>()
                )
            )
        )

        val actualResponses = Responses.Builder(json = json).apply {
            response<TestResponse>(HttpStatusCode.OK)
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
                    mediaTypeExamples = MediaTypeExamples(
                        example = "Example",
                        exampleJson = JsonPrimitive("Example"),
                        examples = emptyMap()
                    )
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

    @Test
    fun `Should create responses with default empty response`() {
        val expectedResponses = Responses(
            responses = listOf(
                Response(
                    responseClass = TestResponse::class,
                    statusCode = null,
                    mediaTypeExamples = MediaTypeExamples<Any>()
                )
            )
        )

        val actualResponses = Responses.Builder(json = json).apply {
            defaultResponse<TestResponse>()
        }.build()

        assertThat(actualResponses).isEqualTo(expectedResponses)
    }

    private object TestResponse
}
