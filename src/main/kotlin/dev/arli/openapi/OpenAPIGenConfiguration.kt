package dev.arli.openapi

import dev.arli.openapi.model.InfoObject

data class OpenAPIGenConfiguration(
    val openAPIVersion: String = "3.0.3",
    var info: InfoObject? = null
) {
    inline fun info(crossinline configure: InfoObject.() -> Unit) {
        info = InfoObject().apply(configure)
    }
}
