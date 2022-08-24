package dev.arli.openapi.routing

import dev.arli.openapi.OpenAPIGen
import dev.arli.openapi.model.RequestBodyExamples
import dev.arli.openapi.model.RequestBodyExamplesBuilder
import dev.arli.openapi.model.Responses
import dev.arli.openapi.model.ResponsesBuilder
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.pluginOrNull
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.server.routing.put
import io.ktor.util.pipeline.PipelineInterceptor

// TODO: handle more parameters
inline fun <reified Request : Any, reified Response : Any> Route.documentedPut(
    path: String = "",
    tags: Set<String> = emptySet(),
    summary: String? = null,
    description: String? = null,
//    externalDocs: ExternalDocumentationObject? = null, // TODO
    operationId: String? = null,
//    callbacks: Map<String, CallbackComponent>? = null, // TODO
    requestBodyExamples: RequestBodyExamplesBuilder = {},
    responses: ResponsesBuilder = {},
    deprecated: Boolean = false,
//    servers: List<ServerObject> = emptyList() // TODO
    noinline body: PipelineInterceptor<Unit, ApplicationCall>
): Route {
    val plugin = requireNotNull(application.pluginOrNull(OpenAPIGen)) {
        "OpenAPI plugin must be initialized"
    }
    return put(path = path, body = body).also {
        plugin.registerRoute(
            route = it,
            requestClass = Request::class,
            responseClass = Response::class,
            tags = tags,
            summary = summary,
            description = description,
            externalDocs = null,
            operationId = operationId,
            requestBodyExamples = RequestBodyExamples.Builder(plugin.json).apply(requestBodyExamples).build(),
            responses = Responses.Builder(plugin.json).apply(responses).build(),
            deprecated = deprecated
        )
    }
}
