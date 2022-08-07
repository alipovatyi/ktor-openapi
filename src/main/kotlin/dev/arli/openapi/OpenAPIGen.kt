package dev.arli.openapi

import dev.arli.openapi.generator.OpenAPIJsonGenerator
import dev.arli.openapi.mapper.OperationMapper
import dev.arli.openapi.mapper.RoutePathMapper
import dev.arli.openapi.model.ExternalDocumentationObject
import dev.arli.openapi.model.PathItemObject
import dev.arli.openapi.model.RequestBodyExamples
import dev.arli.openapi.model.Response
import dev.arli.openapi.model.SecuritySchemeComponent
import io.ktor.http.HttpMethod
import io.ktor.server.application.ApplicationCallPipeline
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.BaseApplicationPlugin
import io.ktor.server.application.hooks.MonitoringEvent
import io.ktor.server.routing.HttpMethodRouteSelector
import io.ktor.server.routing.Route
import io.ktor.util.AttributeKey
import kotlin.reflect.KClass
import kotlinx.serialization.json.Json

class OpenAPIGen(
    private val configuration: OpenAPIGenConfiguration,
    private val openAPIJsonGenerator: OpenAPIJsonGenerator = OpenAPIJsonGenerator(),
    private val routePathMapper: RoutePathMapper = RoutePathMapper(),
    private val operationMapper: OperationMapper = OperationMapper(),
    val json: Json = configuration.json
) {

    private val pathItems = mutableMapOf<String, PathItemObject>()

    fun registerRoute(
        route: Route,
        requestClass: KClass<*>,
        responseClass: KClass<*>,
        tags: Set<String>,
        summary: String?,
        description: String?,
        externalDocs: ExternalDocumentationObject?,
        operationId: String?,
        requestBodyExamples: RequestBodyExamples?,
        responses: List<Response<*, *>>,
        deprecated: Boolean
    ) {
        val path = routePathMapper.map(route)
        val pathItem = pathItems.getOrDefault(path, PathItemObject())
        val method = (route.selector as HttpMethodRouteSelector).method
        val routeToOperationParams = OperationMapper.Params(
            route = route,
            requestClass = requestClass,
            responseClass = responseClass,
            tags = tags,
            summary = summary,
            description = description,
            externalDocs = externalDocs,
            operationId = operationId,
            requestBodyExamples = requestBodyExamples,
            responses = responses,
            deprecated = deprecated
        )
        val operation = operationMapper.map(routeToOperationParams)

        pathItems[path] = pathItem.copy(
            get = if (method == HttpMethod.Get) operation else pathItem.get,
            put = if (method == HttpMethod.Put) operation else pathItem.put,
            post = if (method == HttpMethod.Post) operation else pathItem.post,
            delete = if (method == HttpMethod.Delete) operation else pathItem.delete
        )
    }

    companion object Plugin : BaseApplicationPlugin<ApplicationCallPipeline, OpenAPIGenConfiguration, OpenAPIGen> {

        // TODO: consider moving to the class instead of object
        private val securitySchemes = mutableMapOf<String, SecuritySchemeComponent>()

        private val ApplicationStartedEvent = MonitoringEvent(ApplicationStarted)

        override val key: AttributeKey<OpenAPIGen> = AttributeKey("OpenAPIGen")

        override fun install(
            pipeline: ApplicationCallPipeline,
            configure: OpenAPIGenConfiguration.() -> Unit
        ): OpenAPIGen {
            val configuration = OpenAPIGenConfiguration().apply(configure)
            val plugin = OpenAPIGen(configuration)

            ApplicationStartedEvent.install(pipeline) {
                val openAPIJson = plugin.openAPIJsonGenerator.generate(
                    configuration = configuration,
                    pathItems = plugin.pathItems.toMap()
                )
                // TODO: generate JSON & write to openapi.json
                println(openAPIJson)
            }

            return plugin
        }

        fun registerSecurityScheme(name: String, securityScheme: SecuritySchemeComponent) {
            securitySchemes[name] = securityScheme
        }
    }
}
