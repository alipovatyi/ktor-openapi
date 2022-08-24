package dev.arli.openapi.model

import io.ktor.http.Url

internal data class ServerObject(
    val url: Url,
    var description: String? = null,
    var variables: Map<String, ServerVariableObject> = emptyMap()
)
