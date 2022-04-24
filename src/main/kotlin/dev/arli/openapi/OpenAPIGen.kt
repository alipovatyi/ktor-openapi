package dev.arli.openapi

import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.application.hooks.MonitoringEvent

val OpenAPIGen = createApplicationPlugin(
    name = "OpenAPIGen",
    createConfiguration = ::OpenAPIGenConfiguration
) {
    on(MonitoringEvent(ApplicationStarted)) {
        println(">>> OpenAPIGen installed: $pluginConfig")
    }
}
