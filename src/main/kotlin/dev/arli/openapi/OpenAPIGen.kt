package dev.arli.openapi

import dev.arli.openapi.generator.ContactJsonGenerator
import dev.arli.openapi.generator.InfoJsonGenerator
import dev.arli.openapi.generator.LicenseJsonGenerator
import dev.arli.openapi.generator.OpenAPIJsonGenerator
import dev.arli.openapi.generator.ServerJsonGenerator
import dev.arli.openapi.generator.ServerVariableJsonGenerator
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.application.hooks.MonitoringEvent

val OpenAPIGen = createApplicationPlugin(
    name = "OpenAPIGen",
    createConfiguration = ::OpenAPIGenConfiguration
) {
    val contactJsonGenerator = ContactJsonGenerator()
    val licenseJsonGenerator = LicenseJsonGenerator()
    val infoJsonGenerator = InfoJsonGenerator(
        contactJsonGenerator = contactJsonGenerator,
        licenseJsonGenerator = licenseJsonGenerator
    )
    val serverVariableJsonGenerator = ServerVariableJsonGenerator()
    val serverJsonGenerator = ServerJsonGenerator(
        serverVariableJsonGenerator = serverVariableJsonGenerator
    )
    val openAPIJsonGenerator = OpenAPIJsonGenerator(
        infoJsonGenerator = infoJsonGenerator,
        serverJsonGenerator = serverJsonGenerator
    )

    on(MonitoringEvent(ApplicationStarted)) {
        val openAPIJson = openAPIJsonGenerator.generate(pluginConfig)
        println(openAPIJson) // TODO: remove printing
    }
}
