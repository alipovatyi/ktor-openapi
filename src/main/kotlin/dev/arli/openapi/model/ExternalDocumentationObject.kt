package dev.arli.openapi.model

import io.ktor.http.Url

data class ExternalDocumentationObject(
    val url: Url,
    val description: String? = null
)
