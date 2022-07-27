package dev.arli.openapi.model

import io.ktor.http.HttpStatusCode
import kotlin.reflect.KClass
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

typealias ResponseBuilder<RESPONSE, CONTENT> = Response.Builder<RESPONSE, CONTENT>.() -> Unit

data class Response<RESPONSE : Any, CONTENT> internal constructor(
    val responseClass: KClass<RESPONSE>,
    val statusCode: HttpStatusCode?,
    val example: CONTENT?,
    val exampleJson: JsonElement?,
    val examples: Examples<CONTENT>
) {

    class Builder<RESPONSE : Any, CONTENT>(
        private val json: Json,
        internal val responseClass: KClass<RESPONSE>,
        internal val statusCode: HttpStatusCode?,
    ) {
        var example: CONTENT? = null
        var examples: Examples<CONTENT> = Examples(emptyMap())

        fun examples(builder: ExamplesBuilder<CONTENT>) {
            examples = Examples.Builder<CONTENT>(json).apply(builder).build()
        }

        fun build(exampleJson: JsonElement?): Response<RESPONSE, CONTENT> {
            return Response(
                responseClass = responseClass,
                statusCode = statusCode,
                example = example,
                exampleJson = exampleJson,
                examples = examples
            )
        }
    }
}
