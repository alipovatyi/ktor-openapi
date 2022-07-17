package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.Examples.Companion.examples
import org.junit.jupiter.api.Test

internal class ExamplesTest {

    @Test
    fun `Should create examples`() {
        val expectedExamples = Examples(
            examples = mapOf(
                "example-1" to Example(
                    value = "Example",
                    summary = "Summary",
                    description = null
                ),
                "example-2" to Example(
                    value = 123,
                    summary = null,
                    description = "Description"
                )
            )
        )

        val actualExamples = examples {
            example(name = "example-1", value = "Example") {
                summary = "Summary"
            }
            example(name = "example-2", value = 123) {
                description = "Description"
            }
        }

        assertThat(actualExamples).isEqualTo(expectedExamples)
    }
}
