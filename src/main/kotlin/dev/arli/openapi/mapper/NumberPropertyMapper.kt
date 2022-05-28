package dev.arli.openapi.mapper

import dev.arli.openapi.model.NumberProperty
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import dev.arli.openapi.util.DataType
import dev.arli.openapi.util.getDataType
import dev.arli.openapi.util.getNumberFormat
import kotlin.reflect.KProperty

class NumberPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): NumberProperty {
        require(property.returnType.getDataType() == DataType.NUMBER) {
            "Property [$property] should be of a number data type"
        }

        return NumberProperty(
            name = propertyNameParser.parse(property),
            description = descriptionParser.parse(property),
            nullable = property.returnType.isMarkedNullable,
            format = property.returnType.getNumberFormat()
        )
    }
}
