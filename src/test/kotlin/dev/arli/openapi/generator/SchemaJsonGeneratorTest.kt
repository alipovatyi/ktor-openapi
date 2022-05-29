package dev.arli.openapi.generator

import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.StringFormat
import kotlin.test.assertEquals
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class SchemaJsonGeneratorTest {

    private val generator = SchemaJsonGenerator()

    @Test
    fun `Should convert schema object to json object`() {
        val givenSchemaObject = SchemaObject(
            type = DataType.INTEGER,
            format = IntegerFormat.INT_32,
            nullable = true,
            description = "Description",
            properties = emptyMap(),
            enum = setOf("100", "200", "300")
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "integer")
            put("format", "int32")
            put("nullable", true)
            put("description", "Description")
            putJsonArray("enum") {
                add("100")
                add("200")
                add("300")
            }
        }

        assertEquals(expectedJsonObject, generator.generateSchemaJson(givenSchemaObject))
    }

    @Test
    fun `Should convert schema object with properties to json object`() {
        val givenPropertySchema = SchemaObject(
            type = DataType.STRING,
            format = StringFormat.DATE,
            nullable = true,
            description = "Date",
            properties = emptyMap(),
            enum = emptySet()
        )
        val givenSchemaObject = SchemaObject(
            type = DataType.OBJECT,
            format = null,
            nullable = false,
            description = "Description",
            properties = mapOf("date" to givenPropertySchema),
            enum = emptySet()
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "object")
            put("nullable", false)
            put("description", "Description")
            putJsonObject("properties") {
                put("date", buildJsonObject {
                    put("type", "string")
                    put("format", "date")
                    put("nullable", true)
                    put("description", "Date")
                })
            }
        }

        assertEquals(expectedJsonObject, generator.generateSchemaJson(givenSchemaObject))
    }

    @Test
    fun `Should exclude null values`() {
        val givenPropertySchema = SchemaObject(
            type = DataType.STRING,
            format = StringFormat.DATE,
            nullable = true,
            description = "Date",
            properties = emptyMap(),
            enum = emptySet()
        )
        val givenSchemaObject = SchemaObject(
            type = DataType.INTEGER,
            format = null,
            nullable = false,
            properties = mapOf("date" to givenPropertySchema),
            description = null,
            enum = setOf("100", "200", "300")
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "integer")
            put("nullable", false)
            putJsonObject("properties") {
                put("date", buildJsonObject {
                    put("type", "string")
                    put("format", "date")
                    put("nullable", true)
                    put("description", "Date")
                })
            }
            putJsonArray("enum") {
                add("100")
                add("200")
                add("300")
            }
        }

        assertEquals(expectedJsonObject, generator.generateSchemaJson(givenSchemaObject))
    }

    @Test
    fun `Should exclude empty enum array`() {
        val givenPropertySchema = SchemaObject(
            type = DataType.STRING,
            format = StringFormat.DATE,
            nullable = true,
            description = "Date",
            properties = emptyMap(),
            enum = emptySet()
        )
        val givenSchemaObject = SchemaObject(
            type = DataType.INTEGER,
            format = IntegerFormat.INT_32,
            nullable = false,
            properties = mapOf("date" to givenPropertySchema),
            description = "Description",
            enum = emptySet()
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "integer")
            put("format", "int32")
            put("nullable", false)
            put("description", "Description")
            putJsonObject("properties") {
                put("date", buildJsonObject {
                    put("type", "string")
                    put("format", "date")
                    put("nullable", true)
                    put("description", "Date")
                })
            }
        }

        assertEquals(expectedJsonObject, generator.generateSchemaJson(givenSchemaObject))
    }

    @Test
    fun `Should exclude empty properties`() {
        val givenSchemaObject = SchemaObject(
            type = DataType.INTEGER,
            format = IntegerFormat.INT_64,
            nullable = true,
            description = "Description",
            properties = emptyMap(),
            enum = setOf("100", "200", "300")
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "integer")
            put("format", "int64")
            put("nullable", true)
            put("description", "Description")
            putJsonArray("enum") {
                add("100")
                add("200")
                add("300")
            }
        }

        assertEquals(expectedJsonObject, generator.generateSchemaJson(givenSchemaObject))
    }
}
