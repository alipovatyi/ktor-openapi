package dev.arli.openapi.generator

import dev.arli.openapi.generator.serializer.DynamicLookupSerializer
import dev.arli.openapi.model.ExampleObject
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.putJsonObject

class MediaTypeJsonGenerator(
    private val json: Json = Json,
    private val dynamicLookupSerializer: DynamicLookupSerializer = DynamicLookupSerializer(),
    private val schemaJsonGenerator: SchemaJsonGenerator = SchemaJsonGenerator()
) {

    fun generateMediaTypeJson(mediaType: MediaType, mediaTypeObject: MediaTypeObject): JsonObject {
        return buildJsonObject {
            mediaTypeObject.schema?.let { schema ->
                put("schema", schemaJsonGenerator.generateSchemaJson(schema))
            }
            if (mediaTypeObject.examples.isNotEmpty()) {
                putJsonObject("examples") {
                    mediaTypeObject.examples.forEach { (name, example) ->
                        putJsonObject(name) {
                            val exampleValue = (example as? ExampleObject<*>)?.value
                            if (mediaType == MediaType.APPLICATION_JSON && exampleValue != null) {
                                put("value", json.encodeToJsonElement(dynamicLookupSerializer, exampleValue))
                            } else {
                                // TODO support other media types
                            }
                        }
                    }
                }
            } else {
                mediaTypeObject.example?.let { example ->
                    if (mediaType == MediaType.APPLICATION_JSON) {
                        put("example", json.encodeToJsonElement(dynamicLookupSerializer, example))
                    } else {
                        // TODO support other media types
                    }
                }
            }
        }
    }
}
