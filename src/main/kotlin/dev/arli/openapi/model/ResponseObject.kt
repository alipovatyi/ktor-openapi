package dev.arli.openapi.model

//data class ResponseObject<Response>(
data class ResponseObject(
    val description: String, // REQUIRED
    val headers: Map<String, HeaderComponent> = emptyMap(),
//    val content: Map<MediaType, MediaTypeObject<Response>> = emptyMap(),
//    val content: Map<MediaType, MediaTypeObject<Any>> = emptyMap(),
    val content: Map<MediaType, MediaTypeObject> = emptyMap(),
    val links: Map<String, LinkComponent> = emptyMap() // TODO: map links
) : ResponseComponent
