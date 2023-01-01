package dev.arli.openapi.mapper

import dev.arli.openapi.model.HeaderComponent
import dev.arli.openapi.parser.HeaderNameParser
import kotlin.reflect.KProperty

internal class HeadersMapper(
    private val headerNameParser: HeaderNameParser = HeaderNameParser(),
    private val headerMapper: HeaderMapper = HeaderMapper()
) {

    fun map(annotatedProperties: List<KProperty<*>>): Map<String, HeaderComponent> {
        val headers = mutableMapOf<String, HeaderComponent>()
        val names = mutableSetOf<String>()

        annotatedProperties.forEach { headerProperty ->
            val headerName = headerNameParser.parse(headerProperty)

            require(headerName !in names) {
                "Header name [$headerName] must be unique"
            }

            if (headerName.lowercase() !in ignoredHeaders.map { it.lowercase() }) {
                names += headerName
                headers[headerName] = headerMapper.map(headerProperty)
            }
        }

        return headers
    }

    private companion object {
        val ignoredHeaders = listOf("Content-Type")
    }
}
