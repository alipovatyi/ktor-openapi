package dev.arli.openapi.mapper

import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.EnumProperty
import dev.arli.openapi.model.property.getDataType
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import kotlinx.serialization.SerialName
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

class EnumPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): EnumProperty {
        require(getDataType(property.returnType.jvmErasure) == DataType.ENUM) {
            "Property [$property] should be of an enum type"
        }

        return EnumProperty(
            name = propertyNameParser.parse(property),
            description = descriptionParser.parse(property),
            nullable = property.returnType.isMarkedNullable,
            values = extractEnumValues(property)
        )
    }

    private fun extractEnumValues(property: KProperty<*>): Set<String> {
        return property.returnType.jvmErasure.java.declaredFields
            .filter { it.isEnumConstant }
            .map { it.getAnnotation(SerialName::class.java)?.value ?: it.name }
            .toSet()
    }
}
