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
import dev.arli.openapi.swagger.OAuth2Redirect
import dev.arli.openapi.swagger.SwaggerUI
import io.ktor.http.HttpMethod
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCallPipeline
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.BaseApplicationPlugin
import io.ktor.server.application.call
import io.ktor.server.application.hooks.MonitoringEvent
import io.ktor.server.application.install
import io.ktor.server.application.pluginOrNull
import io.ktor.server.html.respondHtmlTemplate
import io.ktor.server.http.content.file
import io.ktor.server.http.content.static
import io.ktor.server.routing.HttpMethodRouteSelector
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.webjars.Webjars
import io.ktor.util.AttributeKey
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import java.io.File
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
        tags: Set<String>,
        summary: String?,
        description: String?,
        externalDocs: ExternalDocumentation?,
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
            val openAPIMapper = OpenAPIMapper()
            val plugin = OpenAPIGen(configuration)
            val logger = requireNotNull(pipeline.environment?.log) { "Logger must be initialized" }

            ApplicationStartedEvent.install(pipeline) {
                val openAPIObject = openAPIMapper.map(
                    openAPIGenConfiguration = configuration,
                    pathItems = plugin.pathItems.toMap(),
                    securitySchemes = securitySchemes.toMap(),
                    tags = configuration.tags
                )
                val openAPIJson = plugin.openAPIJsonGenerator.generate(openAPIObject)
                logger.info("OpenAPI specification generated successfully: {}", openAPIJson.toString())
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
    pluginOrNull(Webjars) ?: install(Webjars)
    val configuration = openAPIGen.configuration
    val swaggerUIConfiguration = openAPIGen.configuration.swaggerUIConfiguration
    val openAPIFile = File(configuration.outputDir, configuration.outputFileName)
    routing {
        static("/") {
            file(remotePath = configuration.outputFileName, localPath = openAPIFile)
        }
        get(swaggerUIConfiguration.path) {
            val swaggerUI = SwaggerUI(swaggerUIConfiguration = swaggerUIConfiguration)
            call.respondHtmlTemplate(swaggerUI) {}
        }
        get(swaggerUIConfiguration.oauth2RedirectPath) {
            val oAuth2Redirect = OAuth2Redirect()
            call.respondHtmlTemplate(oAuth2Redirect) {}
        }
    }
}
