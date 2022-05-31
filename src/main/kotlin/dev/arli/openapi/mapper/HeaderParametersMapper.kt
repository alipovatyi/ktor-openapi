package dev.arli.openapi.mapper

import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.parser.HeaderParameterNameParser
import kotlin.reflect.KProperty

class HeaderParametersMapper(
    private val headerParameterNameParser: HeaderParameterNameParser = HeaderParameterNameParser(),
    private val headerParameterMapper: HeaderParameterMapper = HeaderParameterMapper()
) {

    fun map(annotatedProperties: List<KProperty<*>>): List<ParameterComponent> {
        val parameters = mutableListOf<ParameterComponent>()
        val names = mutableSetOf<String>()

        annotatedProperties.forEach { headerParameterProperty ->
            val headerParameterName = headerParameterNameParser.parse(headerParameterProperty)

            require(headerParameterName !in names) {
                "Header parameter name [$headerParameterName] must be unique"
            }

            names += headerParameterName

            parameters += headerParameterMapper.map(headerParameterProperty)
        }

        return parameters
    }
}
