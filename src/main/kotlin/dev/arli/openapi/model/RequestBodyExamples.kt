package dev.arli.openapi.model

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

typealias RequestBodyExamplesBuilder = RequestBodyExamples.Builder.() -> Unit

data class RequestBodyExamples internal constructor(
    private val examples: Map<MediaType, MediaTypeExamples<*>> = emptyMap()
) : Map<MediaType, MediaTypeExamples<*>> by examples {

    class Builder(val json: Json) {
        val examples: MutableMap<MediaType, MediaTypeExamples<*>> = mutableMapOf()

        inline fun <reified T> applicationJson(builder: MediaTypeExamplesBuilder<T>) {
            val mediaTypeExamplesBuilder = MediaTypeExamples.Builder<T>(json = json).apply(builder)
            val exampleJson = mediaTypeExamplesBuilder.example?.let(json::encodeToJsonElement)
            examples[MediaType.APPLICATION_JSON] = mediaTypeExamplesBuilder.build(exampleJson)
        }

        // TODO: support other media types

        fun build(): RequestBodyExamples {
            return RequestBodyExamples(examples = examples.toMap())
        }
    }
}
