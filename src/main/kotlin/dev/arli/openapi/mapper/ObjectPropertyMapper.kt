package dev.arli.openapi.mapper

import dev.arli.openapi.model.ObjectProperty
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import dev.arli.openapi.util.DataType
import dev.arli.openapi.util.getDataType
import kotlin.reflect.KProperty

class ObjectPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): ObjectProperty {
        require(property.returnType.getDataType() == DataType.OBJECT) {
            "Property [$property] should be of an object data type"
        }

        return ObjectProperty(
            name = propertyNameParser.parse(property),
            description = descriptionParser.parse(property),
            nullable = property.returnType.isMarkedNullable
        )
    }
}
