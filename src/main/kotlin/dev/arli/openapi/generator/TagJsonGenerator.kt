package dev.arli.openapi.generator

import dev.arli.openapi.model.TagObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal class TagJsonGenerator(
    private val externalDocumentationJsonGenerator: ExternalDocumentationJsonGenerator = ExternalDocumentationJsonGenerator()
) {

    fun generateTagJson(tagObject: TagObject): JsonObject {
        return buildJsonObject {
            put("name", tagObject.name)
            tagObject.description?.let { description ->
                put("description", description)
            }
            tagObject.externalDocs?.let { externalDocs ->
                put("externalDocs", externalDocumentationJsonGenerator.generateExternalDocumentationJson(externalDocs))
            }
        }
    }
}
