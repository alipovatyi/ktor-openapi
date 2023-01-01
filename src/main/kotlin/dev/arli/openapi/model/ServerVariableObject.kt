package dev.arli.openapi.model

internal data class ServerVariableObject(
    var enum: Set<String> = emptySet(), // SHOULD NOT BE EMPTY
    var default: String? = null, // REQUIRED
    var description: String? = null
)
