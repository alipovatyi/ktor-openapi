package dev.arli.openapi.model

import io.ktor.http.HttpStatusCode

data class MediaTypeObject(
    val schema: SchemaComponent? = null,
    val example: Any? = null, // TODO map; example xor examples
    val examples: Map<HttpStatusCode, ExampleComponent> = emptyMap(), // TODO map; example xor examples
//    val encoding: EncodingObject? = null  // TODO
)
