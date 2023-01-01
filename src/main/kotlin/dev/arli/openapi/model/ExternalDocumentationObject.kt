package dev.arli.openapi.model

import io.ktor.http.Url

internal data class ExternalDocumentationObject(
    val url: Url, // REQUIRED
    val description: String? = null
)
