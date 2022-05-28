package dev.arli.openapi.mapper

import dev.arli.openapi.model.SchemaComponent
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.ArrayProperty
import dev.arli.openapi.model.property.BooleanProperty
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerProperty
import dev.arli.openapi.model.property.NumberProperty
import dev.arli.openapi.model.property.ObjectProperty
import dev.arli.openapi.model.property.StringProperty
import dev.arli.openapi.model.property.getDataType
import dev.arli.openapi.util.isEnum
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties

class SchemaComponentMapper(
    private val propertyMapper: PropertyMapper = PropertyMapper()
) {

    fun map(property: KProperty<*>): SchemaComponent {
        val type = property.returnType.getDataType()
        var properties = emptyMap<String, SchemaComponent>()

        if (property.returnType.isEnum.not() && type == DataType.OBJECT) {
//            println(property)
//            properties = property::class.declaredMemberProperties.map(::map)
        }

        return SchemaObject(
            type = type,
//            format = ,
            nullable = property.returnType.isMarkedNullable,
            properties = properties,
            description = null
        )
    }

    private fun foo(kProperty: KProperty<*>) {
        when (val property = propertyMapper.map(kProperty)) {
            is ArrayProperty -> SchemaObject(
                type = property.type,
                format = ,
                nullable = property.returnType.isMarkedNullable,
                properties = properties,
                description = null
            )
            is BooleanProperty -> TODO()
            is IntegerProperty -> TODO()
            is NumberProperty -> TODO()
            is ObjectProperty -> TODO()
            is StringProperty -> TODO()
        }
    }
}
