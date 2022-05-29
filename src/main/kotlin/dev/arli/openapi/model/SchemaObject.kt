package dev.arli.openapi.model

import dev.arli.openapi.model.property.DataFormat
import dev.arli.openapi.model.property.DataType

// TODO: add more properties
data class SchemaObject(
    val type: DataType,
    val format: DataFormat?,
    val nullable: Boolean,
    val description: String?,
    val properties: Map<String, SchemaComponent>,
    val enum: Set<String>
) : SchemaComponent
