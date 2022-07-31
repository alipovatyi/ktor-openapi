package dev.arli.openapi.model

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

typealias MediaTypeExamplesBuilder<T> = MediaTypeExamples.Builder<T>.() -> Unit

data class MediaTypeExamples<T>(
    val example: T? = null,
    val exampleJson: JsonElement? = null,
    val examples: Map<String, Example<T>> = emptyMap()
) {

    class Builder<T>(val json: Json) {
        var example: T? = null
        val examples: MutableMap<String, Example<T>> = mutableMapOf()

        fun build(exampleJson: JsonElement?): MediaTypeExamples<T> {
            return MediaTypeExamples(
                example = example,
                exampleJson = exampleJson,
                examples = examples
            )
        }
    }
}

inline fun <reified T> MediaTypeExamples.Builder<T>.example(name: String, value: T, builder: ExampleBuilder<T> = {}) {
    val valueJson = json.encodeToJsonElement(value)
    val example = Example.Builder(value).apply(builder).build(valueJson)
    examples[name] = example
}
