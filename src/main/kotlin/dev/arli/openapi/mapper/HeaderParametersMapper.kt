package dev.arli.openapi.mapper

import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.parser.HeaderNameParser
import kotlin.reflect.KProperty

internal class HeaderParametersMapper(
    private val headerNameParser: HeaderNameParser = HeaderNameParser(),
    private val headerParameterMapper: HeaderParameterMapper = HeaderParameterMapper()
) {

    fun map(annotatedProperties: List<KProperty<*>>): List<ParameterComponent> {
        val parameters = mutableListOf<ParameterComponent>()
        val names = mutableSetOf<String>()

        annotatedProperties.forEach { headerParameterProperty ->
            val headerParameterName = headerNameParser.parse(headerParameterProperty)

            require(headerParameterName !in names) {
                "Header parameter name [$headerParameterName] must be unique"
            }

            require(headerParameterName.lowercase() !in notAllowedHeaders.map { it.lowercase() }) {
                "Header parameter [$headerParameterName] is not allowed"
            }

            names += headerParameterName
            parameters += headerParameterMapper.map(headerParameterProperty)
        }

        return parameters
    }

    private companion object {
        val notAllowedHeaders = listOf("Accept", "Content-Type", "Authorization")
    }
}
