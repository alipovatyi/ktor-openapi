package dev.arli.openapi.mapper

import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.parser.PathParameterNameParser
import kotlin.reflect.KProperty

internal class PathParametersMapper(
    private val pathParameterNameParser: PathParameterNameParser = PathParameterNameParser(),
    private val pathParameterMapper: PathParameterMapper = PathParameterMapper()
) {

    fun map(pathParameters: Set<String>, annotatedProperties: List<KProperty<*>>): List<ParameterComponent> {
        return pathParameters.map { pathParameterName ->
            val pathParameterProperty = annotatedProperties.find { property ->
                pathParameterNameParser.parse(property) == pathParameterName
            }

            require(pathParameterProperty != null) {
                "Path parameter [$pathParameterName] must be annotated with @Path annotation"
            }

            pathParameterMapper.map(pathParameterProperty)
        }
    }
}
