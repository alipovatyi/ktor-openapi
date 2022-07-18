package dev.arli.openapi.generator

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class MediaTypeJsonGeneratorTest {

    private val generator = MediaTypeJsonGenerator()

    @Test
    fun `Should convert media type object to json object`() {
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
        }

        val actualJsonObject = generator.generateMediaTypeJson(givenMediaType, givenMediaTypeObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }
}
