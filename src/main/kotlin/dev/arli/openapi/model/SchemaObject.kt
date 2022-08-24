package dev.arli.openapi.model

import dev.arli.openapi.model.property.DataFormat
import dev.arli.openapi.model.property.DataType

// TODO: add more properties
internal data class SchemaObject(
    val type: DataType,
    val format: DataFormat?,
    val nullable: Boolean,
    val description: String? = null,
    val properties: Map<String, SchemaComponent> = emptyMap(),
    val additionalProperties: SchemaComponent? = null,
    val enum: Set<String> = emptySet(),
    val items: SchemaComponent? = null
) : SchemaComponent
