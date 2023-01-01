package dev.arli.openapi.generator

import dev.arli.openapi.model.ExternalDocumentationObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal class ExternalDocumentationJsonGenerator {

    fun generateExternalDocumentationJson(externalDocumentation: ExternalDocumentationObject): JsonObject {
        return buildJsonObject {
            put("url", externalDocumentation.url.toString())
            externalDocumentation.description?.let { description ->
                put("description", description)
            }
        }
    }
}
