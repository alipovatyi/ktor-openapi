package dev.arli.openapi

import dev.arli.openapi.model.InfoObject
import dev.arli.openapi.model.ServerObject
import io.ktor.http.Url

data class OpenAPIGenConfiguration(
    val openAPIVersion: String = "3.0.3",
    var info: InfoObject? = null,
    var servers: List<ServerObject> = emptyList()
) {
    inline fun info(crossinline configure: InfoObject.() -> Unit) {
        info = InfoObject().apply(configure)
    }

    inline fun server(url: Url, crossinline configure: ServerObject.() -> Unit) {
        servers = servers + ServerObject(url).apply(configure)
    }
}
