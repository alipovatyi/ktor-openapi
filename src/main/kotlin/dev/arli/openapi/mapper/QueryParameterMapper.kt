package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Query
import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.parser.QueryParameterNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

class QueryParameterMapper(
    private val queryParameterNameParser: QueryParameterNameParser = QueryParameterNameParser(),
    private val schemaComponentMapper: SchemaComponentMapper = SchemaComponentMapper()
) {

    fun map(property: KProperty<*>): ParameterComponent {
        val name = queryParameterNameParser.parse(property)
        val queryAnnotation = requireNotNull(property.findAnnotation<Query>()) {
            "Query parameter [$name] must be annotated with @Query annotation"
        }

        return ParameterObject(
            name = name,
            `in` = ParameterLocation.QUERY,
            required = queryAnnotation.required,
            description = queryAnnotation.description,
            deprecated = queryAnnotation.deprecated,
            schema = schemaComponentMapper.map(property)
        )
    }
}
