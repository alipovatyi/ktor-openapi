package dev.arli.openapi.model

import io.ktor.http.Url

typealias ExternalDocumentationBuilder = ExternalDocumentation.Builder.() -> Unit

data class ExternalDocumentation(
    val url: Url,
    val description: String? = null
) {

    class Builder(private val url: Url) {
        var description: String? = null

        fun build(): ExternalDocumentation {
            return ExternalDocumentation(
                url = url,
                description = description
            )
        }
    }
}
