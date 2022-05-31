package dev.arli.openapi.mapper

import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.parser.CookieParameterNameParser
import kotlin.reflect.KProperty

class CookieParametersMapper(
    private val cookieParameterNameParser: CookieParameterNameParser = CookieParameterNameParser(),
    private val cookieParameterMapper: CookieParameterMapper = CookieParameterMapper()
) {

    fun map(annotatedProperties: List<KProperty<*>>): List<ParameterComponent> {
        val parameters = mutableListOf<ParameterComponent>()
        val names = mutableSetOf<String>()

        annotatedProperties.forEach { cookieParameterProperty ->
            val cookieParameterName = cookieParameterNameParser.parse(cookieParameterProperty)

            require(cookieParameterName !in names) {
                "Cookie parameter name [$cookieParameterName] must be unique"
            }

            names += cookieParameterName

            parameters += cookieParameterMapper.map(cookieParameterProperty)
        }

        return parameters
    }
}
