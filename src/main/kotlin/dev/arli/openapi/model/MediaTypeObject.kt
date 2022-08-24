package dev.arli.openapi.model

import kotlinx.serialization.json.JsonElement

internal data class MediaTypeObject<T>(
    val schema: SchemaComponent? = null,
    val example: T? = null,
    val examples: Map<String, ExampleComponent> = emptyMap(),
//    val encoding: EncodingObject? = null  // TODO
    internal val exampleJson: JsonElement? = null
)
