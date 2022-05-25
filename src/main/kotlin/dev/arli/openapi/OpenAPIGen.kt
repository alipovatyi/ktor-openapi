package dev.arli.openapi

import io.ktor.server.application.ApplicationCallPipeline
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.BaseApplicationPlugin
import io.ktor.server.application.hooks.MonitoringEvent
import io.ktor.util.AttributeKey

class OpenAPIGen(
    private val configuration: OpenAPIGenConfiguration
) {

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
