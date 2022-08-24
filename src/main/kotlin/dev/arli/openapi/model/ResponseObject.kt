package dev.arli.openapi.model

internal data class ResponseObject<CONTENT>(
    val description: String, // REQUIRED
    val headers: Map<String, HeaderComponent> = emptyMap(),
    val content: Map<MediaType, MediaTypeObject<CONTENT>> = emptyMap(),
    val links: Map<String, LinkComponent> = emptyMap() // TODO: map links
) : ResponseComponent
