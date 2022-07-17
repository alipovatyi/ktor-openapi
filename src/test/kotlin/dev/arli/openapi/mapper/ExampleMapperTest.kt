package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.Example
import dev.arli.openapi.model.ExampleObject
import org.junit.jupiter.api.Test

internal class ExampleMapperTest {

    private val mapper = ExampleMapper()

    @Test
    fun `Should map example to example object`() {
        val givenExample = Example(
            value = "Example",
            summary = "Summary",
            description = "Description"
        )

        val expectedExampleObject = ExampleObject(
            value = "Example",
            summary = "Summary",
            description = "Description"
        )

        assertThat(mapper.map(givenExample)).isEqualTo(expectedExampleObject)
    }
}
