package dev.arli.openapi.generator

import dev.arli.openapi.model.HeaderObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import kotlin.test.assertEquals
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class HeaderJsonGeneratorTest {

    private val generator = HeaderJsonGenerator()

    @Test
    fun `Should convert header object to json object`() {
        val givenHeaderObject = HeaderObject(
            description = "Description",
            required = true,
            deprecated = true,
            schema = SchemaObject(
                type = DataType.STRING,
                format = StringFormat.DATE,
                nullable = true
            )
        )

        val expectedJsonObject = buildJsonObject {
            put("description", "Description")
            put("required", true)
            put("deprecated", true)
            putJsonObject("schema") {
                put("type", "string")
                put("format", "date")
                put("nullable", true)
            }
        }

        assertEquals(expectedJsonObject, generator.generateResponseJson(givenHeaderObject))
    }

    @Test
    fun `Should exclude null values`() {
        val givenHeaderObject = HeaderObject()

        val expectedJsonObject = buildJsonObject {
            put("required", false)
            put("deprecated", false)
        }

        assertEquals(expectedJsonObject, generator.generateResponseJson(givenHeaderObject))
    }

    @Test
    fun `Should convert reference object to json object`() {
        // TODO
    }
}
