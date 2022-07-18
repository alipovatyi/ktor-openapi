package dev.arli.openapi.model

data class ExampleObject<T>(
    val value: T,
    val summary: String? = null,
    val description: String? = null,
    val externalValue: String? = null // TODO Url
) : ExampleComponent
