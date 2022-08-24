package dev.arli.openapi.routing

import dev.arli.openapi.OpenAPIGen
import dev.arli.openapi.model.Responses
import dev.arli.openapi.model.ResponsesBuilder
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.pluginOrNull
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.server.routing.get
import io.ktor.util.pipeline.PipelineInterceptor

// TODO: handle more parameters
inline fun <reified Request : Any, reified Response : Any> Route.documentedGet(
    path: String = "",
    tags: Set<String> = emptySet(),
    summary: String? = null,
    description: String? = null,
//    externalDocs: ExternalDocumentationObject? = null, // TODO
    operationId: String? = null,
//    callbacks: Map<String, CallbackComponent>? = null, // TODO
    responses: ResponsesBuilder = {},
    deprecated: Boolean = false,
//    servers: List<ServerObject> = emptyList() // TODO
    noinline body: PipelineInterceptor<Unit, ApplicationCall>
): Route {
    val plugin = requireNotNull(application.pluginOrNull(OpenAPIGen)) {
        "OpenAPI plugin must be initialized"
    }
    return get(path = path, body = body).also {
        plugin.registerRoute(
            route = it,
            requestClass = Request::class,
            responseClass = Response::class,
            tags = tags,
            summary = summary,
            description = description,
            externalDocs = null,
            operationId = operationId,
            requestBodyExamples = null,
            responses = Responses.Builder(plugin.json).apply(responses).build(),
            deprecated = deprecated
        )
    }
}
