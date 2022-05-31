package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Path
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

class PathParameterNameParser {

    fun parse(property: KProperty<*>): String {
        val propertyName = property.name
        val pathAnnotation = requireNotNull(property.findAnnotation<Path>()) {
            "Path parameter [$propertyName] must be annotated with @Path annotation"
        }
        val pathAnnotationName = pathAnnotation.name.takeIf { it.isNotBlank() }
        return pathAnnotationName ?: propertyName
    }
}
