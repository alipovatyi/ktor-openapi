package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import org.junit.jupiter.api.Test

internal class ExamplesTest {

    private val json = Json

    @Test
    fun `Should create examples`() {
        val expectedExamples = Examples(
            examples = mapOf(
                "example-1" to Example(
                    value = "Example 1",
                    summary = "Summary",
                    description = null,
                    valueJson = JsonPrimitive("Example 1")
                ),
                "example-2" to Example(
                    value = "Example 2",
                    valueJson = JsonPrimitive("Example 2"),
                    summary = null,
                    description = "Description"
                )
            )
        )

        val actualExamples = Examples.Builder<String>(json).apply {
            example(name = "example-1", value = "Example 1") {
                summary = "Summary"
            }
            example(name = "example-2", value = "Example 2") {
                description = "Description"
            }
        }.build()

        assertThat(actualExamples).isEqualTo(expectedExamples)
    }
}
