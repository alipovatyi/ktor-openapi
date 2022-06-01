package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Header
import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.parser.HeaderNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

class HeaderParameterMapper(
    private val headerNameParser: HeaderNameParser = HeaderNameParser(),
    private val schemaComponentMapper: SchemaComponentMapper = SchemaComponentMapper()
) {

    fun map(property: KProperty<*>): ParameterComponent {
        val name = headerNameParser.parse(property)
        val headerAnnotation = requireNotNull(property.findAnnotation<Header>()) {
            "Header parameter [$name] must be annotated with @Header annotation"
        }

        return ParameterObject(
            name = name,
            `in` = ParameterLocation.HEADER,
            required = headerAnnotation.required,
            description = headerAnnotation.description,
            deprecated = headerAnnotation.deprecated,
            schema = schemaComponentMapper.map(property)
        )
    }
}
