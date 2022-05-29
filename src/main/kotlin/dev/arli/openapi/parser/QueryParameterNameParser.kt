package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Query
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

class QueryParameterNameParser {

    fun parse(property: KProperty<*>): String {
        val queryAnnotationName = property.findAnnotation<Query>()?.name?.takeIf { it.isNotBlank() }
        val propertyName = property.name
        return queryAnnotationName ?: propertyName
    }
}
