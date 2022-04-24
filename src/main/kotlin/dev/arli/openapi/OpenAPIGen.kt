package dev.arli.openapi

import dev.arli.openapi.generator.ContactJsonGenerator
import dev.arli.openapi.generator.InfoJsonGenerator
import dev.arli.openapi.generator.LicenseJsonGenerator
import dev.arli.openapi.generator.OpenAPIJsonGenerator
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
    val openAPIJsonGenerator = OpenAPIJsonGenerator(
        infoJsonGenerator = infoJsonGenerator
    )

    on(MonitoringEvent(ApplicationStarted)) {
        println(">>> OpenAPIGen installed")
        println(">>> Configuration: $pluginConfig")
        println(">>> OpenAPI JSON: ${openAPIJsonGenerator.generate(pluginConfig)}")
    }
}
