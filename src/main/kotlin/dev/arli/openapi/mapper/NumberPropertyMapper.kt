package dev.arli.openapi.mapper

import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.NumberProperty
import dev.arli.openapi.model.property.getDataType
import dev.arli.openapi.model.property.getNumberFormat
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

class NumberPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): NumberProperty {
        require(getDataType(property.returnType.jvmErasure) == DataType.NUMBER) {
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
