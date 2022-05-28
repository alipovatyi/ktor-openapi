package dev.arli.openapi.mapper

import dev.arli.openapi.model.StringProperty
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import dev.arli.openapi.util.DataType
import dev.arli.openapi.util.getDataType
import dev.arli.openapi.util.getStringFormat
import kotlin.reflect.KProperty

class StringPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): StringProperty {
        require(property.returnType.getDataType() == DataType.STRING) {
            "Property [$property] should be of a string data type"
        }

        return StringProperty(
            name = propertyNameParser.parse(property),
            description = descriptionParser.parse(property),
            nullable = property.returnType.isMarkedNullable,
            format = property.returnType.getStringFormat()
        )
    }
}
