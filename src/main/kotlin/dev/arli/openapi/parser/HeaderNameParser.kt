package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Header
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

internal class HeaderNameParser {

    fun parse(property: KProperty<*>): String {
        val propertyName = property.name
        val headerAnnotation = requireNotNull(property.findAnnotation<Header>()) {
            "Header [$propertyName] must be annotated with @Header annotation"
        }
        val headerAnnotationName = headerAnnotation.name.takeIf { it.isNotBlank() }
        return headerAnnotationName ?: propertyName
    }
}
