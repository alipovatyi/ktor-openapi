package dev.arli.openapi.model

data class MediaTypeObject(
    val schema: SchemaComponent? = null,
    val example: Any? = null, // TODO map; example xor examples
    val examples: Map<String, ExampleComponent> = emptyMap(), // TODO map; example xor examples
//    val encoding: EncodingObject? = null  // TODO
)
