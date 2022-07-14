package dev.arli.openapi.generator

import dev.arli.openapi.model.HeaderComponent
import dev.arli.openapi.model.HeaderObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class HeaderJsonGenerator(
    private val schemaJsonGenerator: SchemaJsonGenerator = SchemaJsonGenerator()
) {

    fun generateResponseJson(header: HeaderComponent): JsonObject {
        return buildJsonObject {
            if (header is HeaderObject) {
                header.description?.let { description ->
                    put("description", description)
                }
                put("required", header.required)
                put("deprecated", header.deprecated)
                header.schema?.let { schema ->
                    put("schema", schemaJsonGenerator.generateSchemaJson(schema))
                }
            }
        }
    }
}
