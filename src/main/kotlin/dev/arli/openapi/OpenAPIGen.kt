package dev.arli.openapi

import dev.arli.openapi.model.ExternalDocumentationObject
import io.ktor.server.application.ApplicationCallPipeline
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.BaseApplicationPlugin
import io.ktor.server.application.hooks.MonitoringEvent
import io.ktor.server.routing.Route
import io.ktor.util.AttributeKey
import kotlin.reflect.KClass

class OpenAPIGen(
    private val configuration: OpenAPIGenConfiguration
) {

    fun registerRoute(
        route: Route,
        requestClass: KClass<*>,
        responseClass: KClass<*>,
        tags: Set<String>,
        summary: String?,
        description: String?,
        externalDocs: ExternalDocumentationObject?,
        operationId: String?,
        deprecated: Boolean
    ) {
        // TODO: not implemented yet
    }

    companion object Plugin : BaseApplicationPlugin<ApplicationCallPipeline, OpenAPIGenConfiguration, OpenAPIGen> {

        private val ApplicationStartedEvent = MonitoringEvent(ApplicationStarted)

        override val key: AttributeKey<OpenAPIGen> = AttributeKey("OpenAPIGen")

        override fun install(
            pipeline: ApplicationCallPipeline,
            configure: OpenAPIGenConfiguration.() -> Unit
        ): OpenAPIGen {
            val configuration = OpenAPIGenConfiguration().apply(configure)
            val plugin = OpenAPIGen(configuration)

            ApplicationStartedEvent.install(pipeline) {
                // TODO: generate JSON & write to openapi.json
            }

            return plugin
        }
    }
}
