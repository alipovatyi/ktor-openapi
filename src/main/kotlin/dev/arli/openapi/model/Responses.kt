package dev.arli.openapi.model

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

typealias ResponsesBuilder = Responses.Builder.() -> Unit

data class Responses(
    private val responses: List<Response<*, *>>
) : List<Response<*, *>> by responses {

    data class Builder(val json: Json) {
        val responses: MutableList<Response<*, *>> = mutableListOf()

        inline fun <reified RESPONSE : Any, reified CONTENT> defaultResponse(
            builder: ResponseBuilder<RESPONSE, CONTENT> = {}
        ) {
            val responseBuilder = Response.Builder<RESPONSE, CONTENT>(
                json = json,
                responseClass = RESPONSE::class,
                statusCode = null
            ).apply(builder)
            val exampleJson = responseBuilder.example?.let(json::encodeToJsonElement)
            val response = responseBuilder.build(exampleJson)
            responses.add(response)
        }

        inline fun <reified RESPONSE : Any, reified CONTENT> response(
            statusCode: HttpStatusCode,
            builder: ResponseBuilder<RESPONSE, CONTENT> = {}
        ) {
            val responseBuilder = Response.Builder<RESPONSE, CONTENT>(
                json = json,
                responseClass = RESPONSE::class,
                statusCode = statusCode
            ).apply(builder)
            val exampleJson = responseBuilder.example?.let(json::encodeToJsonElement)
            val response = responseBuilder.build(exampleJson)
            responses.add(response)
        }

        fun build(): Responses {
            return Responses(responses = responses)
        }
    }
}
