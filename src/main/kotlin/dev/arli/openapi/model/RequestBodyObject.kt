package dev.arli.openapi.model

data class RequestBodyObject(
    val description: String? = null,
    val content: Map<MediaType, MediaTypeObject<*>> = emptyMap(),
    val required: Boolean = false
) : RequestBodyComponent
