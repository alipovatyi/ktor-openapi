package dev.arli.openapi.mapper

import dev.arli.openapi.model.SchemaComponent
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.ArrayProperty
import dev.arli.openapi.model.property.BooleanProperty
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.EnumProperty
import dev.arli.openapi.model.property.IntegerProperty
import dev.arli.openapi.model.property.MapProperty
import dev.arli.openapi.model.property.NumberProperty
import dev.arli.openapi.model.property.ObjectProperty
import dev.arli.openapi.model.property.StringProperty
import dev.arli.openapi.model.property.getDataType
import dev.arli.openapi.model.property.getIntegerFormat
import dev.arli.openapi.model.property.getNumberFormat
import dev.arli.openapi.model.property.getStringFormat
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.jvmErasure

class SchemaComponentMapper(
    private val propertyMapper: PropertyMapper = PropertyMapper()
) {

    fun map(kProperty: KProperty<*>): SchemaComponent {
        return when (val property = propertyMapper.map(kProperty)) {
            is StringProperty -> SchemaObject(
                type = property.type,
                format = property.format,
                nullable = property.nullable,
                properties = emptyMap(),
                description = property.description,
                enum = emptySet()
            )
            is NumberProperty -> SchemaObject(
                type = property.type,
                format = property.format,
                nullable = property.nullable,
                description = property.description,
                properties = emptyMap(),
                enum = emptySet()
            )
            is IntegerProperty -> SchemaObject(
                type = property.type,
                format = property.format,
                nullable = property.nullable,
                description = property.description,
                properties = emptyMap(),
                enum = emptySet()
            )
            is BooleanProperty -> SchemaObject(
                type = property.type,
                format = null,
                nullable = property.nullable,
                description = property.description,
                properties = emptyMap(),
                enum = emptySet()
            )
            is ArrayProperty -> mapArray(property, kProperty)
            is MapProperty -> mapMap(property, kProperty)
            is ObjectProperty -> mapObject(property, kProperty)
            is EnumProperty -> SchemaObject(
                type = property.type,
                format = null,
                nullable = property.nullable,
                description = property.description,
                properties = emptyMap(),
                enum = property.values
            )
        }
    }

    private fun mapProperties(kClass: KClass<*>): Map<String, SchemaComponent> {
        return kClass.declaredMemberProperties.associate { property -> property.name to map(property) }
    }

    private fun mapPropertiesIfNeeded(kClass: KClass<*>): Map<String, SchemaComponent> {
        return if (getDataType(kClass) == DataType.OBJECT) mapProperties(kClass) else emptyMap()
    }

    private fun mapArray(arrayProperty: ArrayProperty, kProperty: KProperty<*>): SchemaComponent {
        val returnType = kProperty.returnType
        val arrayItemType = requireNotNull(returnType.arguments.firstOrNull()?.type) {
            "Missing array item type parameter in [${kProperty.name}] property"
        }
        val arrayItemDataType = getDataType(arrayItemType.jvmErasure)
        val arrayItemProperties = mapPropertiesIfNeeded(arrayItemType.jvmErasure)
        val items = SchemaObject(
            type = arrayItemDataType,
            format = null,
            nullable = arrayItemType.isMarkedNullable,
            properties = arrayItemProperties
        )

        return SchemaObject(
            type = arrayProperty.type,
            format = null,
            nullable = arrayProperty.nullable,
            description = arrayProperty.description,
            properties = emptyMap(),
            enum = emptySet(),
            items = items
        )
    }

    private fun mapMap(mapProperty: MapProperty, kProperty: KProperty<*>): SchemaComponent {
        val returnType = kProperty.returnType
        val arguments = returnType.arguments
        val valueType = requireNotNull(arguments[1].type) { "Value type must not be null" }
        val valueDataType = getDataType(valueType.jvmErasure)
        val valueFormat = when (valueDataType) {
            DataType.INTEGER -> valueType.getIntegerFormat()
            DataType.NUMBER -> valueType.getNumberFormat()
            DataType.STRING -> valueType.getStringFormat()
            else -> null
        }
        val additionalProperties = SchemaObject(
            type = valueDataType,
            format = valueFormat,
            nullable = valueType.isMarkedNullable,
            description = null,
            properties = mapPropertiesIfNeeded(valueType.jvmErasure),
            enum = emptySet(),
            items = null
        )
        return SchemaObject(
            type = mapProperty.type,
            format = null,
            nullable = mapProperty.nullable,
            description = mapProperty.description,
            enum = emptySet(),
            properties = emptyMap(),
            additionalProperties = additionalProperties,
            items = null
        )
    }

    private fun mapObject(objectProperty: ObjectProperty, kProperty: KProperty<*>): SchemaComponent {
        val returnType = kProperty.returnType
        val arguments = returnType.arguments
        val properties = mutableMapOf<String, SchemaComponent>()
        val typeParameters = returnType.jvmErasure.typeParameters
        val types = arguments.mapIndexed { index, kTypeProjection ->
            typeParameters[index].name to kTypeProjection
        }.toMap()

        if (arguments.isNotEmpty()) {
            returnType.jvmErasure.declaredMemberProperties.forEach { property ->
                val propertyReturnType = property.returnType
                val realPropertyType = types[propertyReturnType.toString()]?.type

                if (realPropertyType != null) {
                    val propertyDataType = getDataType(realPropertyType.jvmErasure)
                    val format = when (propertyDataType) {
                        DataType.INTEGER -> realPropertyType.getIntegerFormat()
                        DataType.NUMBER -> realPropertyType.getNumberFormat()
                        DataType.STRING -> realPropertyType.getStringFormat()
                        else -> null
                    }

                    properties[property.name] = SchemaObject(
                        type = propertyDataType,
                        format = format,
                        nullable = realPropertyType.isMarkedNullable,
                        properties = mapPropertiesIfNeeded(realPropertyType.jvmErasure)
                    )
                } else {
                    properties[property.name] = map(property)
                }
            }
        } else {
            properties += mapPropertiesIfNeeded(returnType.jvmErasure)
        }

        return SchemaObject(
            type = objectProperty.type,
            format = null,
            nullable = objectProperty.nullable,
            description = objectProperty.description,
            properties = properties.toMap(),
            enum = emptySet()
        )
    }
}
