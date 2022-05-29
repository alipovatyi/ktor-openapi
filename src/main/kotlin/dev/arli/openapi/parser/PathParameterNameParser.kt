package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Path
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

class PathParameterNameParser {

    fun parse(property: KProperty<*>): String {
        val pathAnnotationName = property.findAnnotation<Path>()?.name?.takeIf { it.isNotBlank() }
        val propertyName = property.name
        return pathAnnotationName ?: propertyName
    }
}
