package dev.arli.openapi.mapper

import dev.arli.openapi.model.IntegerProperty
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import dev.arli.openapi.util.DataType
import dev.arli.openapi.util.getDataType
import dev.arli.openapi.util.getIntegerFormat
import kotlin.reflect.KProperty

class IntegerPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): IntegerProperty {
        require(property.returnType.getDataType() == DataType.INTEGER) {
            "Property [$property] should be of an integer data type"
        }

        return IntegerProperty(
            name = propertyNameParser.parse(property),
            description = descriptionParser.parse(property),
            nullable = property.returnType.isMarkedNullable,
            format = property.returnType.getIntegerFormat()
        )
    }
}
