package dev.arli.openapi.model

typealias TagBuilder = Tag.Builder.() -> Unit

data class Tag(
    val name: String,
    val description: String?,
    val externalDocs: ExternalDocumentationObject?
) {

    class Builder(private val name: String) {
        var description: String? = null
//        var externalDocs: ExternalDocumentationObject? = null // TODO

        fun build(): Tag {
            return Tag(
                name = name,
                description = description,
                externalDocs = null // TODO
            )
        }
    }
}
