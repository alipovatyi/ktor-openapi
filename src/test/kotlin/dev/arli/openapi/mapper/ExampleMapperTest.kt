package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.Example
import dev.arli.openapi.model.ExampleObject
import kotlinx.serialization.json.JsonPrimitive
import org.junit.jupiter.api.Test

internal class ExampleMapperTest {

    private val mapper = ExampleMapper()

    @Test
    fun `Should map example to example object`() {
        val givenExample = Example(
            value = "Example",
            valueJson = JsonPrimitive("Example"),
            summary = "Summary",
            description = "Description"
        )

        val expectedExampleObject = ExampleObject(
            value = "Example",
            summary = "Summary",
            description = "Description",
            externalValue = null,
            valueJson = JsonPrimitive("Example")
        )

        assertThat(mapper.map(givenExample)).isEqualTo(expectedExampleObject)
    }
}
