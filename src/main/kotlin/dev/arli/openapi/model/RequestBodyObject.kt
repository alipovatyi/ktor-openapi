package dev.arli.openapi.model

data class RequestBodyObject<CONTENT>(
    val description: String? = null,
    val content: Map<MediaType, MediaTypeObject<CONTENT>> = emptyMap(),
    val required: Boolean = false
) : RequestBodyComponent
