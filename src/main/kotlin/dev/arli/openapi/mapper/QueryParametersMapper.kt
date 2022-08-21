package dev.arli.openapi.mapper

import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.parser.QueryParameterNameParser
import kotlin.reflect.KProperty

internal class QueryParametersMapper(
    private val queryParameterNameParser: QueryParameterNameParser = QueryParameterNameParser(),
    private val queryParameterMapper: QueryParameterMapper = QueryParameterMapper()
) {

    fun map(annotatedProperties: List<KProperty<*>>): List<ParameterComponent> {
        val parameters = mutableListOf<ParameterComponent>()
        val names = mutableSetOf<String>()

        annotatedProperties.forEach { queryParameterProperty ->
            val queryParameterName = queryParameterNameParser.parse(queryParameterProperty)

            require(queryParameterName !in names) {
                "Query parameter name [$queryParameterName] must be unique"
            }

            names += queryParameterName

            parameters += queryParameterMapper.map(queryParameterProperty)
        }

        return parameters
    }
}
