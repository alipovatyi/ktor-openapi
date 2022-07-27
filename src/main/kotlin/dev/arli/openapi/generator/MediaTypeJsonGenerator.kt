package dev.arli.openapi.generator

import dev.arli.openapi.model.ExampleObject
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.putJsonObject

class MediaTypeJsonGenerator(
    private val schemaJsonGenerator: SchemaJsonGenerator = SchemaJsonGenerator(),
    private val exampleJsonGenerator: ExampleJsonGenerator = ExampleJsonGenerator()
) {

    fun <T> generateMediaTypeJson(mediaType: MediaType, mediaTypeObject: MediaTypeObject<T>): JsonObject {
        return buildJsonObject {
            mediaTypeObject.schema?.let { schema ->
                put("schema", schemaJsonGenerator.generateSchemaJson(schema))
            }
            if (mediaTypeObject.examples.isNotEmpty()) {
                putJsonObject("examples") {
                    mediaTypeObject.examples.forEach { (name, example) ->
                        if (example is ExampleObject<*>) {
                            put(name, exampleJsonGenerator.generateExampleJson(mediaType, example))
                        }
                    }
                }
            } else {
                mediaTypeObject.exampleJson?.let { exampleJson -> put("example", exampleJson) }
            }
        }
    }
}
