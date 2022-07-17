package dev.arli.openapi.model

import io.ktor.http.HttpStatusCode
import kotlin.reflect.KClass

data class Response<T : Any>(
    val responseClass: KClass<T>,
    val statusCode: HttpStatusCode?,
    val example: Any?,
    val examples: Map<String, Example>
) {

    private constructor(builder: Builder<T>) : this(
        responseClass = builder.responseClass,
        statusCode = builder.statusCode,
        example = builder.example,
        examples = builder.examples
    )

    data class Builder<T : Any>(
        internal val responseClass: KClass<T>,
        internal val statusCode: HttpStatusCode?,
        var example: Any? = null,
        var examples: Examples = Examples(emptyMap())
    ) {
        inline fun examples(builder: Examples.Builder.() -> Unit) {
            examples = Examples.examples(builder)
        }

        fun build(): Response<T> {
            return Response(this)
        }
    }

    companion object {
        inline fun <reified T : Any> defaultResponse(builder: Builder<T>.() -> Unit = {}): Response<T> {
            return Builder(responseClass = T::class, statusCode = null).apply(builder).build()
        }

        inline fun <reified T : Any> response(
            statusCode: HttpStatusCode,
            builder: Builder<T>.() -> Unit = {}
        ): Response<T> {
            return Builder(responseClass = T::class, statusCode = statusCode).apply(builder).build()
        }
    }
}
