package dev.arli.openapi.mapper

import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.ObjectProperty
import dev.arli.openapi.model.property.getDataType
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

internal class ObjectPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): ObjectProperty {
        require(getDataType(property.returnType.jvmErasure) == DataType.OBJECT) {
            "Property [$property] should be of an object data type"
        }

        return ObjectProperty(
            name = propertyNameParser.parse(property),
            description = descriptionParser.parse(property),
            nullable = property.returnType.isMarkedNullable
        )
    }
}
