package dev.arli.openapi.mapper

import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.Property
import dev.arli.openapi.model.property.getDataType
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

class PropertyMapper(
    private val stringPropertyMapper: StringPropertyMapper = StringPropertyMapper(),
    private val numberPropertyMapper: NumberPropertyMapper = NumberPropertyMapper(),
    private val integerPropertyMapper: IntegerPropertyMapper = IntegerPropertyMapper(),
    private val booleanPropertyMapper: BooleanPropertyMapper = BooleanPropertyMapper(),
    private val arrayPropertyMapper: ArrayPropertyMapper = ArrayPropertyMapper(),
    private val objectPropertyMapper: ObjectPropertyMapper = ObjectPropertyMapper(),
    private val enumPropertyMapper: EnumPropertyMapper = EnumPropertyMapper()
) {

    fun map(property: KProperty<*>): Property {
        return when (getDataType(property.returnType.jvmErasure)) {
            DataType.STRING -> stringPropertyMapper.map(property)
            DataType.NUMBER -> numberPropertyMapper.map(property)
            DataType.INTEGER -> integerPropertyMapper.map(property)
            DataType.BOOLEAN -> booleanPropertyMapper.map(property)
            DataType.ARRAY -> arrayPropertyMapper.map(property)
            DataType.OBJECT -> objectPropertyMapper.map(property)
            DataType.ENUM -> enumPropertyMapper.map(property)
        }
    }
}
