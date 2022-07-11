package dev.arli.openapi.generator

import dev.arli.openapi.model.MediaTypeObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.putJsonObject

class MediaTypeJsonGenerator(
    private val schemaJsonGenerator: SchemaJsonGenerator = SchemaJsonGenerator()
) {

    fun generateMediaTypeJson(mediaType: MediaTypeObject): JsonObject {
        return buildJsonObject {
            mediaType.schema?.let { schema ->
                put("schema", schemaJsonGenerator.generateSchemaJson(schema))
            }
            if (mediaType.examples.isNotEmpty()) {
                putJsonObject("examples") {
                    // TODO
                }
            } else {
                mediaType.example?.let { example ->
                    // TODO
                }
            }
        }
    }
}
