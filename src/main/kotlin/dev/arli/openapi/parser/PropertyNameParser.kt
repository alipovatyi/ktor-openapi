package dev.arli.openapi.parser

import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation
import kotlinx.serialization.SerialName

class PropertyNameParser {

    fun parse(property: KProperty<*>): String {
        return property.findAnnotation<SerialName>()?.value ?: property.name
    }
}
