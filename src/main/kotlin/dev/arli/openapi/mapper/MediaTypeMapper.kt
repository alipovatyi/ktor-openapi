package dev.arli.openapi.mapper

import dev.arli.openapi.model.MediaTypeExamples
import dev.arli.openapi.model.MediaTypeObject
import kotlin.reflect.KProperty

class MediaTypeMapper(
    private val schemaComponentMapper: SchemaComponentMapper = SchemaComponentMapper(),
    private val exampleMapper: ExampleMapper = ExampleMapper()
) {

    fun <T> map(params: Params<T>): MediaTypeObject<T> {
        val mediaTypeExamples = params.mediaTypeExamples ?: MediaTypeExamples()
        return MediaTypeObject(
            schema = schemaComponentMapper.map(params.kProperty),
            example = mediaTypeExamples.example,
            examples = mediaTypeExamples.examples.mapValues { exampleMapper.map(it.value) },
            exampleJson = mediaTypeExamples.exampleJson
        )
    }

    data class Params<T>(
        val kProperty: KProperty<*>,
        val mediaTypeExamples: MediaTypeExamples<T>?
    )
}
