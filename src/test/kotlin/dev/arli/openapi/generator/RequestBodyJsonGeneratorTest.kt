package dev.arli.openapi.generator

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.RequestBodyObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class RequestBodyJsonGeneratorTest {

    private val generator = RequestBodyJsonGenerator()

    @Test
    fun `Should convert request body object to json object`() {
        val givenRequestBodyObject = RequestBodyObject(
            description = "Description",
            content = mapOf(
                MediaType.APPLICATION_JSON to MediaTypeObject(
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
            ),
            required = true
        )

        val expectedJsonObject = buildJsonObject {
            put("description", "Description")
            putJsonObject("content") {
                putJsonObject("application/json") {
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
            }
            put("required", true)
        }

        val actualJsonObject = generator.generateRequestBodyJson(givenRequestBodyObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should exclude null values`() {
        val givenRequestBodyObject = RequestBodyObject(
            description = null,
            content = mapOf(
                MediaType.APPLICATION_JSON to MediaTypeObject(
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
                    examples = emptyMap()
                )
            ),
            required = false
        )

        val expectedJsonObject = buildJsonObject {
            putJsonObject("content") {
                putJsonObject("application/json") {
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
                }
            }
            put("required", false)
        }

        val actualJsonObject = generator.generateRequestBodyJson(givenRequestBodyObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should convert reference object to json object`() {
        // TODO
    }

    private data class TestContent(val value: String)
}
