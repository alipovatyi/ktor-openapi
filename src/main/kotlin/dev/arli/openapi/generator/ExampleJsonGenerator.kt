package dev.arli.openapi.generator

import dev.arli.openapi.model.ExampleComponent
import dev.arli.openapi.model.ExampleObject
import dev.arli.openapi.model.MediaType
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal class ExampleJsonGenerator {

    fun generateExampleJson(mediaType: MediaType, example: ExampleComponent): JsonObject {
        return buildJsonObject {
            if (example is ExampleObject<*>) {
                example.summary?.let { summary -> put("summary", summary) }
                example.description?.let { description -> put("description", description) }
                if (mediaType == MediaType.APPLICATION_JSON) {
                    example.valueJson?.let { valueJson -> put("value", valueJson) }
                } else {
                    // TODO support other media types
                }
            }
        }
    }
}
