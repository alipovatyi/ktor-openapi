package dev.arli.openapi.routing

import dev.arli.openapi.OpenAPIGen
import dev.arli.openapi.model.ExternalDocumentationObject
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
    tags: Set<String> = emptySet(), // TODO tag object
    summary: String? = null,
    description: String? = null,
    externalDocs: ExternalDocumentationObject? = null,
    operationId: String? = null,
//    callbacks: Map<String, CallbackComponent>? = null,
    responses: ResponsesBuilder = {},
    deprecated: Boolean = false,
//    security: List<SecurityRequirementObject> = emptyList(),
//    servers: List<ServerObject> = emptyList()
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
            externalDocs = externalDocs,
            operationId = operationId,
            responses = Responses.Builder(plugin.json).apply(responses).build(),
            deprecated = deprecated
        )
    }
}
