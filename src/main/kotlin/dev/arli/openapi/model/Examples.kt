package dev.arli.openapi.model

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

typealias ExamplesBuilder<T> = Examples.Builder<T>.() -> Unit

data class Examples<T> internal constructor(
    private val examples: Map<String, Example<T>> = emptyMap()
) : Map<String, Example<T>> by examples {

    class Builder<T>(val json: Json) {
        val examples: MutableMap<String, Example<T>> = mutableMapOf()

        fun build(): Examples<T> {
            return Examples(examples)
        }
    }
}

inline fun <reified T> Examples.Builder<T>.example(name: String, value: T, builder: ExampleBuilder<T> = {}) {
    val valueJson = json.encodeToJsonElement(value)
    val example = Example.Builder(value).apply(builder).build(valueJson)
    examples[name] = example
}
