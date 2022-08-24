package dev.arli.openapi.model.property

internal sealed class Property(val type: DataType) {
    abstract val name: String
    abstract val description: String?
    abstract val nullable: Boolean
}

internal data class StringProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean,
    val format: StringFormat
) : Property(DataType.STRING)

internal data class NumberProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean,
    val format: NumberFormat
) : Property(DataType.NUMBER)

internal data class IntegerProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean,
    val format: IntegerFormat
) : Property(DataType.INTEGER)

internal data class BooleanProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean
) : Property(DataType.BOOLEAN)

internal data class ArrayProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean
) : Property(DataType.ARRAY)

internal data class MapProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean
) : Property(DataType.MAP)

internal data class ObjectProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean
) : Property(DataType.OBJECT)

internal data class EnumProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean,
    val values: Set<String>
) : Property(DataType.ENUM)
