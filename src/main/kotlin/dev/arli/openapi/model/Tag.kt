package dev.arli.openapi.model

import io.ktor.http.Url

typealias TagBuilder = Tag.Builder.() -> Unit

data class Tag(
    val name: String,
    val description: String?,
    val externalDocs: ExternalDocumentation?
) {

    class Builder(private val name: String) {
        var description: String? = null
        var externalDocs: ExternalDocumentation? = null

        inline fun externalDocs(url: Url, builder: ExternalDocumentationBuilder = {}) {
            externalDocs = ExternalDocumentation.Builder(url = url).apply(builder).build()
        }

        fun build(): Tag {
            return Tag(
                name = name,
                description = description,
                externalDocs = externalDocs
            )
        }
    }
}
