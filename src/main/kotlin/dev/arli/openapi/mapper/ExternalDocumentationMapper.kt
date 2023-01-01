package dev.arli.openapi.mapper

import dev.arli.openapi.model.ExternalDocumentation
import dev.arli.openapi.model.ExternalDocumentationObject

internal class ExternalDocumentationMapper {

    fun map(externalDocumentation: ExternalDocumentation): ExternalDocumentationObject {
        return ExternalDocumentationObject(
            url = externalDocumentation.url,
            description = externalDocumentation.description
        )
    }
}
