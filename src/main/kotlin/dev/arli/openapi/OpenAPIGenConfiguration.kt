package dev.arli.openapi

import dev.arli.openapi.model.Info
import dev.arli.openapi.model.InfoBuilder
import dev.arli.openapi.model.Servers
import dev.arli.openapi.model.ServersBuilder
import dev.arli.openapi.model.Tags
import dev.arli.openapi.model.TagsBuilder
import dev.arli.openapi.swagger.SwaggerUIConfiguration
import dev.arli.openapi.swagger.SwaggerUIConfigurationBuilder
import io.ktor.serialization.kotlinx.json.DefaultJson
import kotlinx.serialization.json.Json

data class OpenAPIGenConfiguration(
    var json: Json = DefaultJson,
    val openAPIVersion: String = "3.0.3",
    var info: Info? = null,
    var servers: Servers = Servers(),
    var outputDir: String = "openapi",
    val outputFileName: String = "openapi.json",
    val oauth2RedirectPath: String = "oauth2-redirect",
    var tags: Tags = Tags(),
    var swaggerUIConfiguration: SwaggerUIConfiguration = SwaggerUIConfiguration(
        specificationFileName = outputFileName
    )
) {
    inline fun swaggerUI(crossinline configure: SwaggerUIConfigurationBuilder) {
        swaggerUIConfiguration = SwaggerUIConfiguration.Builder(
            specificationFileName = outputFileName
        ).apply(configure).build()
    }

    inline fun info(title: String, version: String, crossinline builder: InfoBuilder) {
        info = Info.Builder(title = title, version = version).apply(builder).build()
    }

    inline fun servers(crossinline builder: ServersBuilder) {
        servers = Servers.Builder().apply(builder).build()
    }

    inline fun tags(crossinline builder: TagsBuilder) {
        tags = Tags.Builder().apply(builder).build()
    }

    // TODO: external documentation
}
