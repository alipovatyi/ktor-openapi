package dev.arli.openapi.model

import io.ktor.http.HttpStatusCode
import kotlin.reflect.KClass

data class Response<T : Any>(
    val statusCode: HttpStatusCode?,
    val example: T?,
    val examples: List<T>,
    val responseClass: KClass<T>
)

inline fun <reified T : Any> response(
    statusCode: HttpStatusCode,
    example: T? = null,
    examples: List<T> = emptyList()
): Response<T> {
    return Response(
        statusCode = statusCode,
        example = example,
        examples = examples,
        responseClass = T::class
    )
}

inline fun <reified T : Any> defaultResponse(
    example: T? = null,
    examples: List<T> = emptyList()
): Response<T> {
    return Response(
        statusCode = null,
        example = example,
        examples = examples,
        responseClass = T::class
    )
}

fun responses(vararg responses: Response<*>): List<Response<*>> = responses.toList()
