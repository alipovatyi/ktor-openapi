package dev.arli.openapi.model

import io.ktor.http.Url

typealias ServerBuilder = Server.Builder.() -> Unit

data class Server(
    val url: Url, // TODO: should it be just path without base url?
    val description: String?,
    val variables: ServerVariables
) {

    class Builder(private val url: Url) {
        var description: String? = null
        var variables: ServerVariables = ServerVariables()

        inline fun variables(builder: ServerVariablesBuilder) {
            variables = ServerVariables.Builder().apply(builder).build()
        }

        fun build(): Server {
            return Server(
                url = url,
                description = description,
                variables = variables
            )
        }
    }
}
