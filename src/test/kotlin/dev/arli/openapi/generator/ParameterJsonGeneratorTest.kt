package dev.arli.openapi.generator

import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ParameterJsonGeneratorTest {

    private val generator = ParameterJsonGenerator()

    @Test
    fun `Should convert parameter object to json object`() {
        val givenParameterObject = ParameterObject(
            name = "parameter",
            `in` = ParameterLocation.PATH,
            description = "Description",
            required = true,
            deprecated = false,
            schema = SchemaObject(
                type = DataType.INTEGER,
                format = IntegerFormat.INT_64,
                nullable = false,
                description = null,
                properties = emptyMap(),
                enum = emptySet()
            )
        )

        val expectedJsonObject = buildJsonObject {
            put("name", "parameter")
            put("in", "path")
            put("description", "Description")
            put("required", true)
            put("deprecated", false)
            putJsonObject("schema") {
                put("type", "integer")
                put("format", "int64")
                put("nullable", false)
            }
        }

        assertEquals(expectedJsonObject, generator.generateParameterJson(givenParameterObject))
    }

    @Test
    fun `Should convert reference object to json object`() {
        // TODO
    }

    @Test
    fun `Should exclude null values`() {
        val givenParameterObject = ParameterObject(
            name = "parameter",
            `in` = ParameterLocation.QUERY,
            description = null,
            required = true,
            deprecated = false,
            schema = null
        )

        val expectedJsonObject = buildJsonObject {
            put("name", "parameter")
            put("in", "query")
            put("required", true)
            put("deprecated", false)
        }

        assertEquals(expectedJsonObject, generator.generateParameterJson(givenParameterObject))
    }
}
