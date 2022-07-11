package dev.arli.openapi.mapper

import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringProperty
import dev.arli.openapi.model.property.getDataType
import dev.arli.openapi.model.property.getStringFormat
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

class StringPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): StringProperty {
        require(getDataType(property.returnType.jvmErasure) == DataType.STRING) {
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
