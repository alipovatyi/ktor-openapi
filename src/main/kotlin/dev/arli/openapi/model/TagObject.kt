package dev.arli.openapi.model

internal data class TagObject(
    val name: String, // REQUIRED
    val description: String? = null, // TODO
    val externalDocs: ExternalDocumentationObject? = null // TODO
)
