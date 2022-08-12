package dev.arli.openapi.model.property

import com.google.common.truth.Truth.assertThat
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.math.BigDecimal
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

internal class DataTypeTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return correct key for data type`(givenDataType: DataType, expectedKey: String) {
        assertThat(givenDataType.key).isEqualTo(expectedKey)
    }

    @ParameterizedTest
    @MethodSource
    fun `Should return data type`(given: KProperty<*>, expected: DataType) {
        assertThat(getDataType(given.returnType.jvmErasure)).isEqualTo(expected)
    }

    private companion object {

        @JvmStatic
        fun `Should return correct key for data type`() = listOf(
            arguments(DataType.STRING, "string"),
            arguments(DataType.NUMBER, "number"),
            arguments(DataType.INTEGER, "integer"),
            arguments(DataType.BOOLEAN, "boolean"),
            arguments(DataType.ARRAY, "array"),
            arguments(DataType.OBJECT, "object"),
            arguments(DataType.ENUM, "string")
        )

        @JvmStatic
        fun `Should return data type`() = listOf(
            arguments(TestClassWithString::value, DataType.STRING),
            arguments(TestClassWithNullableString::value, DataType.STRING),
            arguments(TestClassWithLocalDate::value, DataType.STRING),
            arguments(TestClassWithNullableLocalDate::value, DataType.STRING),
            arguments(TestClassWithLocalDateTime::value, DataType.STRING),
            arguments(TestClassWithNullableLocalDateTime::value, DataType.STRING),
            arguments(TestClassWithFile::value, DataType.STRING),
            arguments(TestClassWithNullableFile::value, DataType.STRING),
            arguments(TestClassWithFloat::value, DataType.NUMBER),
            arguments(TestClassWithNullableFloat::value, DataType.NUMBER),
            arguments(TestClassWithDouble::value, DataType.NUMBER),
            arguments(TestClassWithNullableDouble::value, DataType.NUMBER),
            arguments(TestClassWithBigDecimal::value, DataType.NUMBER),
            arguments(TestClassWithNullableBigDecimal::value, DataType.NUMBER),
            arguments(TestClassWithInt::value, DataType.INTEGER),
            arguments(TestClassWithNullableInt::value, DataType.INTEGER),
            arguments(TestClassWithLong::value, DataType.INTEGER),
            arguments(TestClassWithNullableLong::value, DataType.INTEGER),
            arguments(TestClassWithBoolean::value, DataType.BOOLEAN),
            arguments(TestClassWithNullableBoolean::value, DataType.BOOLEAN),
            arguments(TestClassWithEnum::value, DataType.ENUM),
            arguments(TestClassWithNullableEnum::value, DataType.ENUM),
            arguments(TestClassWithArray::value, DataType.ARRAY),
            arguments(TestClassWithNullableArray::value, DataType.ARRAY),
            arguments(TestClassWithList::value, DataType.ARRAY),
            arguments(TestClassWithNullableList::value, DataType.ARRAY),
            arguments(TestClassWithSet::value, DataType.ARRAY),
            arguments(TestClassWithNullableSet::value, DataType.ARRAY),
            arguments(TestClassWithMap::value, DataType.MAP),
            arguments(TestClassWithNullableMap::value, DataType.MAP),
            arguments(TestClassWithAny::value, DataType.OBJECT),
            arguments(TestClassWithNullableAny::value, DataType.OBJECT)
        )
    }

    private data class TestClassWithString(val value: String)
    private data class TestClassWithNullableString(val value: String?)
    private data class TestClassWithLocalDate(val value: LocalDate)
    private data class TestClassWithNullableLocalDate(val value: LocalDate?)
    private data class TestClassWithLocalDateTime(val value: LocalDateTime)
    private data class TestClassWithNullableLocalDateTime(val value: LocalDateTime?)
    private data class TestClassWithFile(val value: File)
    private data class TestClassWithNullableFile(val value: File?)
    private data class TestClassWithFloat(val value: Float)
    private data class TestClassWithNullableFloat(val value: Float?)
    private data class TestClassWithDouble(val value: Double)
    private data class TestClassWithNullableDouble(val value: Double?)
    private data class TestClassWithBigDecimal(val value: BigDecimal)
    private data class TestClassWithNullableBigDecimal(val value: BigDecimal?)
    private data class TestClassWithInt(val value: Int)
    private data class TestClassWithNullableInt(val value: Int?)
    private data class TestClassWithLong(val value: Long)
    private data class TestClassWithNullableLong(val value: Long?)
    private data class TestClassWithBoolean(val value: Boolean)
    private data class TestClassWithNullableBoolean(val value: Boolean?)
    private data class TestClassWithEnum(val value: TestEnum)
    private data class TestClassWithNullableEnum(val value: TestEnum?)
    private data class TestClassWithArray(val value: Array<Any>)
    private data class TestClassWithNullableArray(val value: Array<Any>?)
    private data class TestClassWithList(val value: List<Any>)
    private data class TestClassWithNullableList(val value: List<Any>?)
    private data class TestClassWithSet(val value: Set<Any>)
    private data class TestClassWithNullableSet(val value: Set<Any>?)
    private data class TestClassWithMap(val value: Map<Any, Any>)
    private data class TestClassWithNullableMap(val value: Map<Any, Any>?)
    private data class TestClassWithAny(val value: Any)
    private data class TestClassWithNullableAny(val value: Any?)

    private enum class TestEnum
}
