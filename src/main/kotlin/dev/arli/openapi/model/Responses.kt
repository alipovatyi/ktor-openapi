package dev.arli.openapi.model

import io.ktor.http.HttpStatusCode

data class Responses(
    private val responses: List<Response<*>> = emptyList()
) : List<Response<*>> by responses {

    private constructor(builder: Builder) : this(responses = builder.responses)

    data class Builder(val responses: MutableList<Response<*>> = mutableListOf()) {

        inline fun <reified T : Any> defaultResponse(builder: Response.Builder<T>.() -> Unit = {}) {
            responses.add(Response.defaultResponse(builder))
        }

        inline fun <reified T : Any> response(
            statusCode: HttpStatusCode,
            builder: Response.Builder<T>.() -> Unit = {}
        ) {
            responses.add(Response.response(statusCode, builder))
        }

        fun build(): Responses {
            return Responses(this)
        }
    }

    companion object {
        inline fun responses(builder: Builder.() -> Unit): Responses {
            return Builder().apply(builder).build()
        }
    }
}
