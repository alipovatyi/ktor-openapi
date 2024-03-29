package dev.arli.openapi.parser

import dev.arli.openapi.annotation.Description
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

internal class DescriptionParser {

    fun parse(property: KProperty<*>): String? {
        return property.findAnnotation<Description>()?.value
    }
}
