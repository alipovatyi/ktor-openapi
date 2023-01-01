package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.junit.jupiter.api.Test

internal class ExampleTest {

    private val json = Json

    @Test
    fun `Should create example with primitive value`() {
        val givenValue = "Value"
        val givenValueJson = json.encodeToJsonElement(givenValue)

        val expectedExample = Example(
            value = givenValue,
            summary = "Summary",
            description = "Description",
            valueJson = givenValueJson
        )

        val actualExample = Example.Builder(value = givenValue).apply {
            summary = "Summary"
            description = "Description"
        }.build(givenValueJson)

        assertThat(actualExample).isEqualTo(expectedExample)
    }

    @Test
    fun `Should create example with object value`() {
        val givenValue = TestClass(123)
        val givenValueJson = json.encodeToJsonElement(givenValue)

        val expectedExample = Example(
            value = givenValue,
            valueJson = givenValueJson,
            summary = "Summary",
            description = "Description"
        )

        val actualExample = Example.Builder(value = givenValue).apply {
            summary = "Summary"
            description = "Description"
        }.build(givenValueJson)

        assertThat(actualExample).isEqualTo(expectedExample)
    }

    @Test
    fun `Should create example with default values`() {
        val givenValue = "Value"
        val givenValueJson = json.encodeToJsonElement(givenValue)

        val expectedExample = Example(
            value = givenValue,
            valueJson = givenValueJson,
            summary = null,
            description = null
        )

        val actualExample = Example.Builder(value = givenValue).build(givenValueJson)

        assertThat(actualExample).isEqualTo(expectedExample)
    }

    @Serializable
    private data class TestClass(val value: Int)
}
