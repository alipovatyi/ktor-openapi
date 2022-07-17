package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.Example.Companion.example
import org.junit.jupiter.api.Test

internal class ExampleTest {

    @Test
    fun `Should create example`() {
        val expectedExample = Example(
            value = "Value",
            summary = "Summary",
            description = "Description"
        )

        val actualExample = example("Value") {
            summary = "Summary"
            description = "Description"
        }

        assertThat(actualExample).isEqualTo(expectedExample)
    }

    @Test
    fun `Should create example with default values`() {
        val expectedExample = Example(
            value = "Value",
            summary = null,
            description = null
        )

        val actualExample = example("Value")

        assertThat(actualExample).isEqualTo(expectedExample)
    }
}
