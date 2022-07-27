package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.put
import org.junit.jupiter.api.Test

internal class ResponseTest {

    private val json = Json

    @Test
    fun `Should create response with single primitive example`() {
        val givenExample = "Example"
        val givenExampleJson = json.encodeToJsonElement(givenExample)

        val expectedResponse = Response(
            statusCode = HttpStatusCode.Created,
            example = givenExample,
            exampleJson = JsonPrimitive(givenExample),
            examples = Examples(emptyMap()),
            responseClass = TestResponse::class
        )

        val actualResponse = Response.Builder<TestResponse, String>(
            json = json,
            responseClass = TestResponse::class,
            statusCode = HttpStatusCode.Created
        ).apply {
            example = "Example"
        }.build(exampleJson = givenExampleJson)

        assertThat(actualResponse).isEqualTo(expectedResponse)
    }

    @Test
    fun `Should create response with single object example`() {
        val givenExample = TestContent("Example")
        val givenExampleJson = json.encodeToJsonElement(givenExample)

        val expectedResponse = Response(
            statusCode = HttpStatusCode.Created,
            example = givenExample,
            exampleJson = buildJsonObject { put("value", "Example") },
            examples = Examples(emptyMap()),
            responseClass = TestResponse::class
        )

        val actualResponse = Response.Builder<TestResponse, TestContent>(
            json = json,
            responseClass = TestResponse::class,
            statusCode = HttpStatusCode.Created
        ).apply {
            example = givenExample
        }.build(exampleJson = givenExampleJson)

        assertThat(actualResponse).isEqualTo(expectedResponse)
    }

    @Test
    fun `Should create response with multiple primitive examples`() {
        val expectedResponse = Response(
            statusCode = HttpStatusCode.Created,
            example = null,
            exampleJson = null,
            examples = Examples(
                examples = mapOf(
                    "example-1" to Example(
                        value = "Example 1",
                        valueJson = JsonPrimitive("Example 1"),
                        description = null,
                        summary = null
                    ),
                    "example-2" to Example(
                        value = "Example 2",
                        valueJson = JsonPrimitive("Example 2"),
                        description = null,
                        summary = null
                    )
                )
            ),
            responseClass = TestResponse::class
        )

        val actualResponse = Response.Builder<TestResponse, String>(
            json = json,
            responseClass = TestResponse::class,
            statusCode = HttpStatusCode.Created
        ).apply {
            examples {
                example(name = "example-1", value = "Example 1")
                example(name = "example-2", value = "Example 2")
            }
        }.build(exampleJson = null)

        assertThat(actualResponse).isEqualTo(expectedResponse)
    }

    @Test
    fun `Should create response with multiple object examples`() {
        val expectedResponse = Response(
            statusCode = HttpStatusCode.Created,
            example = null,
            exampleJson = null,
            examples = Examples(
                examples = mapOf(
                    "example-1" to Example(
                        value = TestContent("Example 1"),
                        valueJson = buildJsonObject { put("value", "Example 1") },
                        description = null,
                        summary = null
                    ),
                    "example-2" to Example(
                        value = TestContent("Example 2"),
                        valueJson = buildJsonObject { put("value", "Example 2") },
                        description = null,
                        summary = null
                    )
                )
            ),
            responseClass = TestResponse::class
        )

        val actualResponse = Response.Builder<TestResponse, TestContent>(
            json = json,
            responseClass = TestResponse::class,
            statusCode = HttpStatusCode.Created
        ).apply {
            examples {
                example(name = "example-1", value = TestContent("Example 1"))
                example(name = "example-2", value = TestContent("Example 2"))
            }
        }.build(exampleJson = null)

        assertThat(actualResponse).isEqualTo(expectedResponse)
    }

    @Test
    fun `Should create response with default values`() {
        val expectedResponse = Response(
            responseClass = TestResponse::class,
            statusCode = HttpStatusCode.Created,
            example = null,
            exampleJson = null,
            examples = Examples(emptyMap())
        )

        val actualResponse = Response.Builder<TestResponse, TestContent>(
            json = json,
            responseClass = TestResponse::class,
            statusCode = HttpStatusCode.Created
        ).build(exampleJson = null)

        assertThat(actualResponse).isEqualTo(expectedResponse)
    }

    private object TestResponse

    @Serializable
    private data class TestContent(val value: String)
}
