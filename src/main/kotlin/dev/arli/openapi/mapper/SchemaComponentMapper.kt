package dev.arli.openapi.mapper

import dev.arli.openapi.model.SchemaComponent
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.ArrayProperty
import dev.arli.openapi.model.property.BooleanProperty
import dev.arli.openapi.model.property.EnumProperty
import dev.arli.openapi.model.property.IntegerProperty
import dev.arli.openapi.model.property.NumberProperty
import dev.arli.openapi.model.property.ObjectProperty
import dev.arli.openapi.model.property.StringProperty
import dev.arli.openapi.parser.DescriptionParser
import dev.arli.openapi.parser.PropertyNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.jvmErasure

class SchemaComponentMapper(
    private val propertyMapper: PropertyMapper = PropertyMapper(),
    private val propertyNameParser: PropertyNameParser = PropertyNameParser(),
    private val descriptionParser: DescriptionParser = DescriptionParser()
) {

    fun map(kProperty: KProperty<*>): SchemaComponent {
        val nullable = kProperty.returnType.isMarkedNullable
        val description = descriptionParser.parse(kProperty)

        return when (val property = propertyMapper.map(kProperty)) {
            is StringProperty -> SchemaObject(
                type = property.type,
                format = property.format,
                nullable = nullable,
                properties = emptyMap(),
                description = description,
                enum = emptySet()
            )
            is NumberProperty -> SchemaObject(
                type = property.type,
                format = property.format,
                nullable = nullable,
                description = description,
                properties = emptyMap(),
                enum = emptySet()
            )
            is IntegerProperty -> SchemaObject(
                type = property.type,
                format = property.format,
                nullable = nullable,
                description = description,
                properties = emptyMap(),
                enum = emptySet()
            )
            is BooleanProperty -> SchemaObject(
                type = property.type,
                format = null,
                nullable = nullable,
                description = description,
                properties = emptyMap(),
                enum = emptySet()
            )
            is ArrayProperty -> SchemaObject(
                type = property.type,
                format = null,
                nullable = nullable,
                description = description,
                properties = emptyMap(),
                enum = emptySet()
            )
            is ObjectProperty -> {
                val properties = kProperty.returnType.jvmErasure.declaredMemberProperties
                    .associateBy(propertyNameParser::parse)
                    .map { (name, property) -> name to map(property) }
                    .toMap()
                SchemaObject(
                    type = property.type,
                    format = null,
                    nullable = nullable,
                    description = description,
                    properties = properties,
                    enum = emptySet()
                )
            }
            is EnumProperty -> SchemaObject(
                type = property.type,
                format = null,
                nullable = nullable,
                description = description,
                properties = emptyMap(),
                enum = property.values
            )
        }
    }
}
