package dev.arli.openapi.generator

import dev.arli.openapi.model.SchemaComponent
import dev.arli.openapi.model.SchemaObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject

class SchemaJsonGenerator {

    fun generateSchemaJson(schema: SchemaComponent): JsonObject {
        return buildJsonObject {
            if (schema is SchemaObject) {
                put("type", schema.type.key)
                schema.format?.let { format ->
                    put("format", format.key)
                }
                put("nullable", schema.nullable)
                schema.description?.let { description ->
                    put("description", description)
                }
                if (schema.properties.isNotEmpty()) {
                    putJsonObject("properties") {
                        schema.properties.forEach { (name, property) ->
                            put(name, generateSchemaJson(property))
                        }
                    }
                }
                schema.additionalProperties?.let { additionalProperties ->
                    put("additionalProperties", generateSchemaJson(additionalProperties))
                }
                if (schema.enum.isNotEmpty()) {
                    putJsonArray("enum") {
                        schema.enum.forEach { enum -> add(enum) }
                    }
                }
                schema.items?.let { items ->
                    put("items", generateSchemaJson(items))
                }
            }
        }
    }
}
