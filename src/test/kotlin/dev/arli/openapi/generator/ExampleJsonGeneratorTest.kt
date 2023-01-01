package dev.arli.openapi.generator

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.ExampleObject
import dev.arli.openapi.model.MediaType
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.jupiter.api.Test

internal class ExampleJsonGeneratorTest {

    private val generator = ExampleJsonGenerator()

    @Test
    fun `Should convert example object to json object`() {
        val givenMediaType = MediaType.APPLICATION_JSON
        val givenExampleObject = ExampleObject(
            value = "Example",
            summary = "Summary",
            description = "Description",
            externalValue = null,
            valueJson = JsonPrimitive("Example")
        )

        val expectedJsonObject = buildJsonObject {
            put("value", "Example")
            put("summary", "Summary")
            put("description", "Description")
        }

        val actualJsonObject = generator.generateExampleJson(givenMediaType, givenExampleObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should exclude null values`() {
        val givenMediaType = MediaType.APPLICATION_JSON
        val givenExampleObject = ExampleObject(
            value = "Example",
            summary = null,
            description = null,
            externalValue = null,
            valueJson = JsonPrimitive("Example")
        )

        val expectedJsonObject = buildJsonObject {
            put("value", "Example")
        }

        val actualJsonObject = generator.generateExampleJson(givenMediaType, givenExampleObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should convert reference object to json object`() {
        // TODO
    }
}
