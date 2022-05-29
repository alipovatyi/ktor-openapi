package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.parser.PathParameterNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

class PathParameterMapper(
    private val pathParameterNameParser: PathParameterNameParser = PathParameterNameParser(),
    private val schemaComponentMapper: SchemaComponentMapper = SchemaComponentMapper()
) {

    fun map(property: KProperty<*>): ParameterComponent {
        val name = pathParameterNameParser.parse(property)
        val pathAnnotation = requireNotNull(property.findAnnotation<Path>() ) {
            "Path parameter [$name] must be annotated with @Path annotation"
        }

        require(property.returnType.isMarkedNullable.not()) {
            "Path parameter [$name] cannot be nullable"
        }

        return ParameterObject(
            name = name,
            `in` = ParameterLocation.PATH,
            required = true,
            description = pathAnnotation.description,
            deprecated = pathAnnotation.deprecated,
            schema = schemaComponentMapper.map(property)
        )
    }
}
