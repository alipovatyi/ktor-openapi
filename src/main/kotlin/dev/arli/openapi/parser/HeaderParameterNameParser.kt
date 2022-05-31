package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Header
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

class HeaderParameterNameParser {

    fun parse(property: KProperty<*>): String {
        val propertyName = property.name
        val headerAnnotation = requireNotNull(property.findAnnotation<Header>()) {
            "Header parameter [$propertyName] must be annotated with @Header annotation"
        }
        val headerAnnotationName = headerAnnotation.name.takeIf { it.isNotBlank() }
        val name = headerAnnotationName ?: propertyName

        require(name.lowercase() !in forbiddenHeaders.map { it.lowercase() }) {
            "Header parameter named [$name] is not allowed"
        }

        return headerAnnotationName ?: propertyName
    }

    private companion object {
        val forbiddenHeaders = listOf("Accept", "Content-Type", "Authorization")
    }
}
