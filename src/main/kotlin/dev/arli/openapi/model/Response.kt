package dev.arli.openapi.model

import io.ktor.http.HttpStatusCode
import kotlin.reflect.KClass
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

typealias ResponseBuilder<RESPONSE, CONTENT> = Response.Builder<RESPONSE, CONTENT>.() -> Unit

data class Response<RESPONSE : Any, CONTENT> internal constructor(
    val responseClass: KClass<RESPONSE>,
    val statusCode: HttpStatusCode?,
    val mediaTypeExamples: MediaTypeExamples<CONTENT>
) {

    class Builder<RESPONSE : Any, CONTENT>(
        json: Json,
        internal val responseClass: KClass<RESPONSE>,
        internal val statusCode: HttpStatusCode?
    ) {
        private val mediaTypeExamplesBuilder: MediaTypeExamples.Builder<CONTENT> = MediaTypeExamples.Builder(json)
        var example: CONTENT?
            get() = mediaTypeExamplesBuilder.example
            set(value) {
                mediaTypeExamplesBuilder.example = value
            }

        fun examples(builder: MediaTypeExamplesBuilder<CONTENT>) {
            mediaTypeExamplesBuilder.apply(builder)
        }

        fun build(exampleJson: JsonElement?): Response<RESPONSE, CONTENT> {
            return Response(
                responseClass = responseClass,
                statusCode = statusCode,
                mediaTypeExamples = mediaTypeExamplesBuilder.build(exampleJson)
            )
        }
    }
}
