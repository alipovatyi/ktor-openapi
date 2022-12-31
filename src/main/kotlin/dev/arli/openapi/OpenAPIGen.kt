package dev.arli.openapi

import dev.arli.openapi.generator.OpenAPIJsonGenerator
import dev.arli.openapi.mapper.OpenAPIMapper
import dev.arli.openapi.mapper.OperationMapper
import dev.arli.openapi.mapper.RoutePathMapper
import dev.arli.openapi.model.ExternalDocumentation
import dev.arli.openapi.model.PathItemObject
import dev.arli.openapi.model.RequestBodyExamples
import dev.arli.openapi.model.Response
import dev.arli.openapi.model.SecuritySchemeComponent
import io.ktor.http.HttpMethod
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCallPipeline
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.BaseApplicationPlugin
import io.ktor.server.application.hooks.MonitoringEvent
import io.ktor.server.application.install
import io.ktor.server.application.pluginOrNull
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.HttpMethodRouteSelector
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import io.ktor.util.AttributeKey
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import java.io.File
import kotlin.collections.set
import kotlin.reflect.KClass

class OpenAPIGen(
    internal val configuration: OpenAPIGenConfiguration,
    val json: Json = configuration.json
) {
    private val openAPIJsonGenerator: OpenAPIJsonGenerator = OpenAPIJsonGenerator()
    private val routePathMapper: RoutePathMapper = RoutePathMapper()
    private val operationMapper: OperationMapper = OperationMapper()

    private val pathItems = mutableMapOf<String, PathItemObject>()

    fun registerRoute(
        route: Route,
        requestClass: KClass<*>,
        responseClass: KClass<*>,
        externalDocs: ExternalDocumentation?,
        requestBodyExamples: RequestBodyExamples?,
        responses: List<Response<*, *>>
    ) {
        val path = routePathMapper.map(route)
        val pathItem = pathItems.getOrDefault(path, PathItemObject())
        val method = (route.selector as HttpMethodRouteSelector).method
        val routeToOperationParams = OperationMapper.Params(
            route = route,
            requestClass = requestClass,
            responseClass = responseClass,
            externalDocs = externalDocs,
            requestBodyExamples = requestBodyExamples,
            responses = responses
        )
        val operation = operationMapper.map(routeToOperationParams)

        pathItems[path] = pathItem.copy(
            get = if (method == HttpMethod.Get) operation else pathItem.get,
            put = if (method == HttpMethod.Put) operation else pathItem.put,
            post = if (method == HttpMethod.Post) operation else pathItem.post,
            delete = if (method == HttpMethod.Delete) operation else pathItem.delete,
            head = if (method == HttpMethod.Head) operation else pathItem.head
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
            val openAPIMapper = OpenAPIMapper()
            val plugin = OpenAPIGen(configuration)

            ApplicationStartedEvent.install(pipeline) {
                val openAPIObject = openAPIMapper.map(
                    openAPIGenConfiguration = configuration,
                    pathItems = plugin.pathItems.toMap(),
                    securitySchemes = securitySchemes.toMap(),
                    tags = configuration.tags
                )
                val openAPIJson = plugin.openAPIJsonGenerator.generate(openAPIObject)
                writeOpenAPIJson(
                    outputDirName = configuration.outputDir,
                    outputFileName = configuration.outputFileName,
                    openAPIJson = openAPIJson
                )
            }

            return plugin
        }

        internal fun registerSecurityScheme(name: String, securityScheme: SecuritySchemeComponent) {
            securitySchemes[name] = securityScheme
        }

        private fun writeOpenAPIJson(
            outputDirName: String,
            outputFileName: String,
            openAPIJson: JsonElement
        ) {
            val outputDir = File(outputDirName)
            if (outputDir.exists().not()) {
                outputDir.mkdirs()
            }
            val outputFile = File(outputDir, outputFileName)
            outputFile.writeText(openAPIJson.toString())
        }
    }
}

fun Application.openAPIGen(block: OpenAPIGenConfiguration.() -> Unit) {
    val openAPIGen = pluginOrNull(OpenAPIGen) ?: install(OpenAPIGen, block)
    val configuration = openAPIGen.configuration
    val swaggerUIConfiguration = openAPIGen.configuration.swaggerUIConfiguration
    val openAPIFile = File(configuration.outputDir, configuration.outputFileName)
    if (swaggerUIConfiguration != null) {
        routing {
            swaggerUI(path = swaggerUIConfiguration.path, apiFile = openAPIFile)
        }
    }
}
