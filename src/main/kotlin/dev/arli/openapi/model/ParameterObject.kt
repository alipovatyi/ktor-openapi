package dev.arli.openapi.model

data class ParameterObject(
    val name: String, // REQUIRED
    val `in`: ParameterLocation, // REQUIRED
    val description: String? = null,
    val required: Boolean = false, // REQUIRED for "path" (true)
    val deprecated: Boolean = false,
    // SCHEMA
//    val style: String, // TODO
//    val explode: Boolean, // TODO
//    val allowReserved: Boolean, // TODO
    val schema: SchemaComponent? = null, // TODO
//    val example: Any? = null, // TODO
//    val examples: Map<String, ExampleComponent> = emptyMap(), // TODO
    // CONTENT
//    val content: Map<String, Any /* MediaTypeObject */> = emptyMap() // TODO
) : ParameterComponent