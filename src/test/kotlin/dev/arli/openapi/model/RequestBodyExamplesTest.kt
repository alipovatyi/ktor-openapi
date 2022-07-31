package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import org.junit.jupiter.api.Test

internal class RequestBodyExamplesTest {

    private val json = Json

    @Test
    fun `Should create request body examples with primitive`() {
        val expectedRequestBodyExamples = RequestBodyExamples(
            examples = mapOf(
                MediaType.APPLICATION_JSON to MediaTypeExamples(
                    example = "Example",
                    exampleJson = JsonPrimitive("Example"),
                    examples = emptyMap()
                )
            )
        )

        val actualRequestBodyExamples = RequestBodyExamples.Builder(json = json).apply {
            applicationJson<String> {
                example = "Example"
            }
        }.build()

        assertThat(actualRequestBodyExamples).isEqualTo(expectedRequestBodyExamples)
    }

    @Test
    fun `Should create request body examples with object`() {
        val expectedRequestBodyExamples = RequestBodyExamples(
            examples = mapOf(
                MediaType.APPLICATION_JSON to MediaTypeExamples(
                    example = "Example",
                    exampleJson = JsonPrimitive("Example"),
                    examples = emptyMap()
                )
            )
        )

        val actualRequestBodyExamples = RequestBodyExamples.Builder(json = json).apply {
            applicationJson<String> {
                example = "Example"
            }
        }.build()

        assertThat(actualRequestBodyExamples).isEqualTo(expectedRequestBodyExamples)
    }
}
