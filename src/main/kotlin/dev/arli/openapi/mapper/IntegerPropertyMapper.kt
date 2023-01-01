package dev.arli.openapi.mapper

import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerProperty
import dev.arli.openapi.model.property.getDataType
import dev.arli.openapi.model.property.getIntegerFormat
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

internal class IntegerPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): IntegerProperty {
        require(getDataType(property.returnType.jvmErasure) == DataType.INTEGER) {
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
