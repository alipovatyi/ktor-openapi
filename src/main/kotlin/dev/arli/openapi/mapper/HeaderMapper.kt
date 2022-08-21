package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Header
import dev.arli.openapi.model.HeaderComponent
import dev.arli.openapi.model.HeaderObject
import dev.arli.openapi.parser.HeaderNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

internal class HeaderMapper(
    private val headerNameParser: HeaderNameParser = HeaderNameParser(),
    private val schemaComponentMapper: SchemaComponentMapper = SchemaComponentMapper()
) {

    fun map(property: KProperty<*>): HeaderComponent {
        val name = headerNameParser.parse(property)
        val headerAnnotation = requireNotNull(property.findAnnotation<Header>()) {
            "Header [$name] must be annotated with @Header annotation"
        }

        return HeaderObject(
            description = headerAnnotation.description,
            required = headerAnnotation.required,
            deprecated = headerAnnotation.deprecated,
            schema = schemaComponentMapper.map(property)
        )
    }
}
