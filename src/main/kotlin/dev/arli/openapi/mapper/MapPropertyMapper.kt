package dev.arli.openapi.mapper

import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.MapProperty
import dev.arli.openapi.model.property.getDataType
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

class MapPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): MapProperty {
        require(getDataType(property.returnType.jvmErasure) == DataType.MAP) {
            "Property [$property] should be of a map type"
        }

        return MapProperty(
            name = propertyNameParser.parse(property),
            description = descriptionParser.parse(property),
            nullable = property.returnType.isMarkedNullable
        )
    }
}
