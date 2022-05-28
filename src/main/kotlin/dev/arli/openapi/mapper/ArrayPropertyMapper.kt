package dev.arli.openapi.mapper

import dev.arli.openapi.model.ArrayProperty
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import dev.arli.openapi.util.DataType
import dev.arli.openapi.util.getDataType
import kotlin.reflect.KProperty

class ArrayPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): ArrayProperty {
        require(property.returnType.getDataType() == DataType.ARRAY) {
            "Property [$property] should be of an array data type"
        }

        return ArrayProperty(
            name = propertyNameParser.parse(property),
            description = descriptionParser.parse(property),
            nullable = property.returnType.isMarkedNullable
        )
    }
}
