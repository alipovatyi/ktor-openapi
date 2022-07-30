package dev.arli.openapi.mapper

import dev.arli.openapi.model.Examples
import dev.arli.openapi.model.MediaTypeObject
import kotlin.reflect.KProperty
import kotlinx.serialization.json.JsonElement

class MediaTypeMapper(
    private val schemaComponentMapper: SchemaComponentMapper = SchemaComponentMapper(),
    private val exampleMapper: ExampleMapper = ExampleMapper()
) {

    fun <T> map(params: Params<T>): MediaTypeObject<T> {
        return MediaTypeObject(
            schema = schemaComponentMapper.map(params.kProperty),
            example = params.example,
            examples = params.examples.mapValues { exampleMapper.map(it.value) },
            exampleJson = params.exampleJson
        )
    }

    data class Params<T>(
        val kProperty: KProperty<*>,
        val example: T?,
        val exampleJson: JsonElement?,
        val examples: Examples<T>
    )
}
