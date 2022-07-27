package dev.arli.openapi.model

import kotlinx.serialization.json.JsonElement

data class MediaTypeObject<T>(
    val schema: SchemaComponent? = null,
    val example: T? = null, // TODO map; example xor examples
    val examples: Map<String, ExampleComponent> = emptyMap(), // TODO map; example xor examples
//    val encoding: EncodingObject? = null  // TODO
    internal val exampleJson: JsonElement? = null
)
