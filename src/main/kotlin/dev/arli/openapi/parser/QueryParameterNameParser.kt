package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Query
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

class QueryParameterNameParser {

    fun parse(property: KProperty<*>): String {
        val propertyName = property.name
        val queryAnnotation = requireNotNull(property.findAnnotation<Query>()) {
            "Query parameter [$propertyName] must be annotated with @Query annotation"
        }
        val queryAnnotationName = queryAnnotation.name.takeIf { it.isNotBlank() }
        return queryAnnotationName ?: propertyName
    }
}
