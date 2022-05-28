package dev.arli.openapi.model.property

sealed class Property(val type: DataType) {
    abstract val name: String
    abstract val description: String?
    abstract val nullable: Boolean
}

data class StringProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean,
    val format: StringFormat
) : Property(DataType.STRING)

data class NumberProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean,
    val format: NumberFormat
) : Property(DataType.NUMBER)

data class IntegerProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean,
    val format: IntegerFormat
) : Property(DataType.INTEGER)

data class BooleanProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean
) : Property(DataType.BOOLEAN)

data class ArrayProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean
) : Property(DataType.ARRAY)

data class ObjectProperty(
    override val name: String,
    override val description: String?,
    override val nullable: Boolean
) : Property(DataType.OBJECT)
