package dev.arli.openapi.model

import kotlinx.serialization.json.JsonElement

data class ExampleObject<T>(
    val value: T,
    val summary: String? = null,
    val description: String? = null,
    val externalValue: String? = null, // TODO Url
    internal val valueJson: JsonElement? = null
) : ExampleComponent
