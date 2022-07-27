package dev.arli.openapi.generator

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.HeaderObject
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.ResponseObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class ResponseJsonGeneratorTest {

    private val generator = ResponseJsonGenerator()

    @Test
    fun `Should convert response object to json object`() {
        val givenResponseObject = ResponseObject(
            description = "Description",
            headers = mapOf("Authorization" to HeaderObject()),
            content = mapOf(
                MediaType.APPLICATION_JSON to MediaTypeObject<String>(
                    schema = SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.DATE,
                        nullable = false
                    )
                )
            ),
            links = emptyMap() // TODO
        )

        val expectedJsonObject = buildJsonObject {
            put("description", "Description")
            putJsonObject("headers") {
                putJsonObject("Authorization") {
                    put("required", false)
                    put("deprecated", false)
                }
            }
            putJsonObject("content") {
                putJsonObject("application/json") {
                    putJsonObject("schema") {
                        put("type", "string")
                        put("format", "date")
                        put("nullable", false)
                    }
                }
            }
        }

        assertThat(generator.generateResponseJson(givenResponseObject)).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should convert reference object to json object`() {
        // TODO
    }
}
