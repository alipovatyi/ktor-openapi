package dev.arli.openapi.mapper

import dev.arli.openapi.model.MediaTypeObject
import kotlin.reflect.KProperty

class MediaTypeMapper(
    private val schemaComponentMapper: SchemaComponentMapper = SchemaComponentMapper()
) {

    fun map(kProperty: KProperty<*>): MediaTypeObject {
        return MediaTypeObject(
            schema = schemaComponentMapper.map(kProperty),
            example = null, // TODO: map example
            examples = emptyMap() // TODO: map examples
        )
    }
}
