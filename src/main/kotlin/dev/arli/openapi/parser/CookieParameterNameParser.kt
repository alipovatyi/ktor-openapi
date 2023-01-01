package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Cookie
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

internal class CookieParameterNameParser {

    fun parse(property: KProperty<*>): String {
        val propertyName = property.name
        val cookieAnnotation = requireNotNull(property.findAnnotation<Cookie>()) {
            "Cookie parameter [$propertyName] must be annotated with @Cookie annotation"
        }
        val cookieAnnotationName = cookieAnnotation.name.takeIf { it.isNotBlank() }
        return cookieAnnotationName ?: propertyName
    }
}
