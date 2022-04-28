package dev.arli.openapi.model

import io.ktor.http.Url

data class ServerObject(
    val url: Url,
    var description: String? = null,
    var variables: Map<String, ServerVariableObject> = emptyMap()
) {
    inline fun variable(name: String, crossinline configure: ServerVariableObject.() -> Unit) {
        variables = variables + (name to ServerVariableObject().apply(configure))
    }
}
