package dev.arli.openapi.generator

import dev.arli.openapi.model.RequestBodyComponent
import dev.arli.openapi.model.RequestBodyObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject

class RequestBodyJsonGenerator(
    private val mediaTypeJsonGenerator: MediaTypeJsonGenerator = MediaTypeJsonGenerator()
) {

    fun generateRequestBodyJson(requestBody: RequestBodyComponent): JsonObject {
        return buildJsonObject {
            if (requestBody is RequestBodyObject<*>) {
                requestBody.description?.let { description ->
                    put("description", description)
                }
                putJsonObject("content") {
                    requestBody.content.forEach { (mediaType, mediaTypeObject) ->
                        put(mediaType.key, mediaTypeJsonGenerator.generateMediaTypeJson(mediaType, mediaTypeObject))
                    }
                }
                put("required", requestBody.required)
            }
        }
    }
}
