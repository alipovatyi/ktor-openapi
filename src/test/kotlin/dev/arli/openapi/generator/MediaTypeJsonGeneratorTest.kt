package dev.arli.openapi.generator

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.ExampleObject
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class MediaTypeJsonGeneratorTest {

    private val generator = MediaTypeJsonGenerator()

    @Test
    fun `Should convert media type object to json object with single example`() {
        val givenMediaType = MediaType.APPLICATION_JSON
        val givenMediaTypeObject = MediaTypeObject(
            schema = SchemaObject(
                type = DataType.OBJECT,
                format = null,
                nullable = false,
                properties = mapOf(
                    "property" to SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.DATE,
                        nullable = true
                    )
                )
            ),
            example = TestContent("Example"),
            exampleJson = buildJsonObject { put("value", "Example") },
            examples = emptyMap()
        )

        val expectedJsonObject = buildJsonObject {
            putJsonObject("schema") {
                put("type", "object")
                put("nullable", false)
                putJsonObject("properties") {
                    putJsonObject("property") {
                        put("type", "string")
                        put("format", "date")
                        put("nullable", true)
                    }
                }
            }
            putJsonObject("example") {
                put("value", "Example")
            }
        }

        val actualJsonObject = generator.generateMediaTypeJson(givenMediaType, givenMediaTypeObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should convert media type object to json object with multiple examples`() {
        val givenMediaType = MediaType.APPLICATION_JSON
        val givenMediaTypeObject = MediaTypeObject(
            schema = SchemaObject(
                type = DataType.OBJECT,
                format = null,
                nullable = false,
                properties = mapOf(
                    "property" to SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.DATE,
                        nullable = true
                    )
                )
            ),
            example = TestContent("Example"),
            exampleJson = buildJsonObject { put("value", "Example") },
            examples = mapOf(
                "example-1" to ExampleObject(value = "Example 1", valueJson = JsonPrimitive("Example 1")),
                "example-2" to ExampleObject(value = "Example 2", valueJson = JsonPrimitive("Example 2"))
            )
        )

        val expectedJsonObject = buildJsonObject {
            putJsonObject("schema") {
                put("type", "object")
                put("nullable", false)
                putJsonObject("properties") {
                    putJsonObject("property") {
                        put("type", "string")
                        put("format", "date")
                        put("nullable", true)
                    }
                }
            }
            putJsonObject("examples") {
                putJsonObject("example-1") {
                    put("value", "Example 1")
                }
                putJsonObject("example-2") {
                    put("value", "Example 2")
                }
            }
        }

        val actualJsonObject = generator.generateMediaTypeJson(givenMediaType, givenMediaTypeObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should convert media type object to json object with multiple examples if provided both example and examples`() {
        val givenMediaType = MediaType.APPLICATION_JSON
        val givenMediaTypeObject = MediaTypeObject(
            schema = SchemaObject(
                type = DataType.OBJECT,
                format = null,
                nullable = false,
                properties = mapOf(
                    "property" to SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.DATE,
                        nullable = true
                    )
                )
            ),
            example = null,
            exampleJson = null,
            examples = mapOf(
                "example-1" to ExampleObject(value = "Example 1", valueJson = JsonPrimitive("Example 1")),
                "example-2" to ExampleObject(value = "Example 2", valueJson = JsonPrimitive("Example 2"))
            )
        )

        val expectedJsonObject = buildJsonObject {
            putJsonObject("schema") {
                put("type", "object")
                put("nullable", false)
                putJsonObject("properties") {
                    putJsonObject("property") {
                        put("type", "string")
                        put("format", "date")
                        put("nullable", true)
                    }
                }
            }
            putJsonObject("examples") {
                putJsonObject("example-1") {
                    put("value", "Example 1")
                }
                putJsonObject("example-2") {
                    put("value", "Example 2")
                }
            }
        }

        val actualJsonObject = generator.generateMediaTypeJson(givenMediaType, givenMediaTypeObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should exclude null values`() {
        val givenMediaType = MediaType.APPLICATION_JSON
        val givenMediaTypeObject = MediaTypeObject(
            schema = null,
            example = null,
            exampleJson = null,
            examples = emptyMap()
        )

        val expectedJsonObject = buildJsonObject {}

        val actualJsonObject = generator.generateMediaTypeJson(givenMediaType, givenMediaTypeObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    private data class TestContent(val value: String)
}
