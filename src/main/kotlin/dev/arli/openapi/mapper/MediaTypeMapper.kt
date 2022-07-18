package dev.arli.openapi.mapper

import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.Response
import kotlin.reflect.KProperty

class MediaTypeMapper(
    private val schemaComponentMapper: SchemaComponentMapper = SchemaComponentMapper(),
    private val exampleMapper: ExampleMapper = ExampleMapper()
) {

    fun map(kProperty: KProperty<*>, response: Response<*>): MediaTypeObject {
        return MediaTypeObject(
            schema = schemaComponentMapper.map(kProperty),
            example = response.example,
            examples = response.examples.mapValues { exampleMapper.map(it.value) }
        )
    }
}
