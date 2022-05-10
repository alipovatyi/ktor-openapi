package dev.arli.openapi.model

data class RequestBodyObject(
    val description: String? = null,
    val content: Map<String, Any /* MediaTypeObject */> = emptyMap(), // TODO
    val required: Boolean = false
) : RequestBodyComponent
