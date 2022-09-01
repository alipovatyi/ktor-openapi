package dev.arli.openapi.model

internal data class HeaderObject(
    val description: String? = null,
    val required: Boolean = false,
    val deprecated: Boolean = false,
    // SCHEMA
//    val style: String, // TODO
//    val explode: Boolean, // TODO
//    val allowReserved: Boolean, // TODO
    val schema: SchemaComponent? = null
//    val example: Any? = null, // TODO
//    val examples: Map<String, ExampleComponent> = emptyMap(), // TODO
    // CONTENT
//    val content: Map<String, Any /* MediaTypeObject */> = emptyMap() // TODO
) : HeaderComponent
