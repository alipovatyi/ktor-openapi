package dev.arli.openapi.generator

import dev.arli.openapi.model.ResponseComponent
import dev.arli.openapi.model.ResponseObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject

class ResponseJsonGenerator(
    private val headerJsonGenerator: HeaderJsonGenerator = HeaderJsonGenerator(),
    private val mediaTypeJsonGenerator: MediaTypeJsonGenerator = MediaTypeJsonGenerator()
) {

    fun generateResponseJson(response: ResponseComponent): JsonObject {
        return buildJsonObject {
            if (response is ResponseObject) {
                put("description", response.description)
                if (response.headers.isNotEmpty()) {
                    putJsonObject("headers") {
                        response.headers.forEach { (name, header) ->
                            put(name, headerJsonGenerator.generateResponseJson(header))
                        }
                    }
                }
                putJsonObject("content") {
                    response.content.forEach { (mediaType, mediaTypeObject) ->
                        put(mediaType.key, mediaTypeJsonGenerator.generateMediaTypeJson(mediaTypeObject))
                    }
                }
                if (response.links.isNotEmpty()) {
                    putJsonObject("links") {
                        // TODO
                    }
                }
            }
        }
    }
}
