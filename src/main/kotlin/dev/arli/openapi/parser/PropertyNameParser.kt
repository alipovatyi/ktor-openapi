package dev.arli.openapi.parser

import kotlinx.serialization.SerialName
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

class PropertyNameParser {

    fun parse(property: KProperty<*>): String {
        return property.findAnnotation<SerialName>()?.value ?: property.name
    }
}
