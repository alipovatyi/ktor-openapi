package dev.arli.openapi.generator

import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.model.ParameterObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal class ParameterJsonGenerator(
    private val schemaJsonGenerator: SchemaJsonGenerator = SchemaJsonGenerator()
) {

    fun generateParameterJson(parameter: ParameterComponent): JsonObject {
        return buildJsonObject {
            if (parameter is ParameterObject) {
                put("name", parameter.name)
                put("in", parameter.`in`.key)
                parameter.description?.let { description ->
                    put("description", description)
                }
                put("required", parameter.required)
                put("deprecated", parameter.deprecated)
                parameter.schema?.let { schema ->
                    put("schema", schemaJsonGenerator.generateSchemaJson(schema))
                }
            }
        }
    }
}
