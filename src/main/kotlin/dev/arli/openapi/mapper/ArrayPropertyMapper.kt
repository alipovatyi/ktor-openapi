package dev.arli.openapi.mapper

import dev.arli.openapi.model.property.ArrayProperty
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.getDataType
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

internal class ArrayPropertyMapper(
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(property: KProperty<*>): ArrayProperty {
        require(getDataType(property.returnType.jvmErasure) == DataType.ARRAY) {
            "Property [$property] should be of an array data type"
        }

        return ArrayProperty(
            name = propertyNameParser.parse(property),
            description = descriptionParser.parse(property),
            nullable = property.returnType.isMarkedNullable
        )
    }
}
