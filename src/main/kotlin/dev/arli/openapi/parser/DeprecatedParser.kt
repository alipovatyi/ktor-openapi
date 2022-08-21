package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Deprecated
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

internal class DeprecatedParser {

    fun parse(property: KProperty<*>): Boolean {
        return property.findAnnotation<Deprecated>() != null
    }
}
