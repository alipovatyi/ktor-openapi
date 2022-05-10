package dev.arli.openapi.model

data class ResponseObject(
    val description: String, // REQUIRED
    val headers: Map<String, HeaderComponent> = emptyMap(),
    val content: Map<String, Any /* ReferenceObject, MediaTypeObject */> = emptyMap(), // TODO
    val links: Map<String, LinkComponent> = emptyMap()
) : ResponseComponent
