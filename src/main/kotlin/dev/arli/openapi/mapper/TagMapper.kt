package dev.arli.openapi.mapper

import dev.arli.openapi.model.Tag
import dev.arli.openapi.model.TagObject

class TagMapper {

    fun map(tag: Tag): TagObject {
        return TagObject(
            name = tag.name,
            description = tag.description,
            externalDocs = tag.externalDocs
        )
    }
}
