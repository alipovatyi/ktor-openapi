package dev.arli.openapi.model

data class ExampleObject<T>(
    val summary: String? = null,
    val description: String? = null,
    val value: T? = null,
    val externalValue: String? = null // TODO Url
) : ExampleComponent
