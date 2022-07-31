package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.jupiter.api.Test

internal class MediaTypeExamplesTest {

    private val json = Json

    @Test
    fun `Should create media type examples with primitive`() {
        val expectedMediaTypeExamples = MediaTypeExamples(
            example = "Example 1",
            exampleJson = JsonPrimitive("Example 1"),
            examples = mapOf(
                "example-2" to Example(
                    value = "Example 2",
                    valueJson = JsonPrimitive("Example 2"),
                    description = "Description",
                    summary = null
                ),
                "example-3" to Example(
                    value = "Example 3",
                    valueJson = JsonPrimitive("Example 3"),
                    description = null,
                    summary = "Summary"
                )
            )
        )

        val actualMediaTypeExamples = MediaTypeExamples.Builder<String>(json = json).apply {
            example = "Example 1"
            example("example-2", "Example 2") {
                description = "Description"
            }
            example("example-3", "Example 3") {
                summary = "Summary"
            }
        }.build(exampleJson = JsonPrimitive("Example 1"))

        assertThat(actualMediaTypeExamples).isEqualTo(expectedMediaTypeExamples)
    }

    @Test
    fun `Should create media type examples with object`() {
        val expectedMediaTypeExamples = MediaTypeExamples(
            example = TestClass("Example 1"),
            exampleJson = buildJsonObject { put("value", "Example 1") },
            examples = mapOf(
                "example-2" to Example(
                    value = TestClass("Example 2"),
                    valueJson = buildJsonObject { put("value", "Example 2") },
                    description = null,
                    summary = "Summary"
                ),
                "example-3" to Example(
                    value = TestClass("Example 3"),
                    valueJson = buildJsonObject { put("value", "Example 3") },
                    description = "Description",
                    summary = null
                )
            )
        )

        val actualMediaTypeExamples = MediaTypeExamples.Builder<TestClass>(json = json).apply {
            example = TestClass("Example 1")
            example("example-2", TestClass("Example 2")) {
                summary = "Summary"
            }
            example("example-3", TestClass("Example 3")) {
                description = "Description"
            }
        }.build(exampleJson = buildJsonObject { put("value", "Example 1") })

        assertThat(actualMediaTypeExamples).isEqualTo(expectedMediaTypeExamples)
    }

    @Serializable
    private data class TestClass(val value: String)
}
