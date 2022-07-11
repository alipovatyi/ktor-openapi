package dev.arli.openapi.mapper

import dev.arli.openapi.model.property.BooleanProperty
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.getDataType
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

class BooleanPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): BooleanProperty {
        require(getDataType(property.returnType.jvmErasure) == DataType.BOOLEAN) {
            "Property [$property] should be of a boolean data type"
        }

        return BooleanProperty(
            name = propertyNameParser.parse(property),
            description = descriptionParser.parse(property),
            nullable = property.returnType.isMarkedNullable
        )
    }
}
