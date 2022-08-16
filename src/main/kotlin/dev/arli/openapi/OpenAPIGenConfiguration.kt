package dev.arli.openapi

import dev.arli.openapi.model.InfoObject
import dev.arli.openapi.model.ServerObject
import dev.arli.openapi.swagger.SwaggerUIConfiguration
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.DefaultJson
import kotlinx.serialization.json.Json

data class OpenAPIGenConfiguration(
    var json: Json = DefaultJson,
    val openAPIVersion: String = "3.0.3",
    var info: InfoObject? = null,
    var servers: List<ServerObject> = emptyList(),
    var outputDir: String = "openapi",
    val outputFileName: String = "openapi.json",
    val oauth2RedirectPath: String = "oauth2-redirect",
    var swaggerUIConfiguration: SwaggerUIConfiguration = SwaggerUIConfiguration(
        specificationFileName = outputFileName
    )
) {
    inline fun swaggerUI(crossinline configure: SwaggerUIConfiguration.() -> Unit) {
        swaggerUIConfiguration = swaggerUIConfiguration.apply(configure)
    }

    inline fun info(crossinline configure: InfoObject.() -> Unit) {
        info = InfoObject().apply(configure)
    }

    // TODO: should it be just path without base url?
    inline fun server(url: Url, crossinline configure: ServerObject.() -> Unit) {
        servers = servers + ServerObject(url).apply(configure)
    }

    // TODO: external documentation
}
