package dev.arli.openapi.model

import kotlinx.serialization.json.JsonElement

typealias ExampleBuilder<T> = Example.Builder<T>.() -> Unit

data class Example<T> internal constructor(
    val value: T,
    val valueJson: JsonElement,
    val description: String?,
    val summary: String?
) {

    class Builder<T>(private val value: T) {
        var description: String? = null
        var summary: String? = null

        fun build(valueJson: JsonElement): Example<T> {
            return Example(
                value = value,
                valueJson = valueJson,
                description = description,
                summary = summary
            )
        }
    }
}
