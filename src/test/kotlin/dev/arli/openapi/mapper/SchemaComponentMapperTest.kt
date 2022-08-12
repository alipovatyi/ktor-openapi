package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.NumberFormat
import dev.arli.openapi.model.property.StringFormat
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test

internal class SchemaComponentMapperTest {

    private val mapper = SchemaComponentMapper()

    @Test
    fun `Should map string property to schema object`() {
        val givenProperty = TestClassWithString::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.STRING,
            format = StringFormat.NO_FORMAT,
            nullable = false,
            description = null,
            properties = emptyMap(),
            enum = emptySet()
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map number property to schema object`() {
        val givenProperty = TestClassWithNumber::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.NUMBER,
            format = NumberFormat.FLOAT,
            nullable = false,
            description = null,
            properties = emptyMap(),
            enum = emptySet()
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map integer property to schema object`() {
        val givenProperty = TestClassWithInteger::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.INTEGER,
            format = IntegerFormat.INT_64,
            nullable = false,
            description = null,
            properties = emptyMap(),
            enum = emptySet()
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map boolean property to schema object`() {
        val givenProperty = TestClassWithBoolean::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.BOOLEAN,
            format = null,
            nullable = false,
            description = null,
            properties = emptyMap(),
            enum = emptySet()
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map primitive array property to schema object`() {
        val givenProperty = TestClassWithPrimitiveArray::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.ARRAY,
            format = null,
            nullable = false,
            description = null,
            properties = emptyMap(),
            enum = emptySet(),
            items = SchemaObject(
                type = DataType.STRING,
                format = null,
                nullable = false
            )
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map object array property to schema object`() {
        val givenProperty = TestClassWithObjectArray::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.ARRAY,
            format = null,
            nullable = false,
            description = null,
            properties = emptyMap(),
            enum = emptySet(),
            items = SchemaObject(
                type = DataType.OBJECT,
                format = null,
                nullable = false,
                description = null,
                properties = mapOf(
                    "property1" to SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = false,
                        description = null,
                        properties = emptyMap(),
                        enum = emptySet()
                    ),
                    "property2" to SchemaObject(
                        type = DataType.INTEGER,
                        format = IntegerFormat.INT_32,
                        nullable = false,
                        description = null,
                        properties = emptyMap(),
                        enum = emptySet()
                    )
                ),
                enum = emptySet()
            )
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map map property to schema object`() {
        val givenProperty = TestClassWithMap::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.MAP,
            format = null,
            nullable = false,
            description = null,
            properties = emptyMap(),
            additionalProperties = SchemaObject(
                type = DataType.INTEGER,
                format = IntegerFormat.INT_32,
                nullable = false,
                description = null,
                properties = emptyMap(),
                enum = emptySet()
            ),
            enum = emptySet(),
            items = null
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map object property to schema object`() {
        val givenProperty = TestClassWithObject::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.OBJECT,
            format = null,
            nullable = false,
            description = null,
            properties = mapOf(
                "property1" to SchemaObject(
                    type = DataType.STRING,
                    format = StringFormat.NO_FORMAT,
                    nullable = false,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                ),
                "property2" to SchemaObject(
                    type = DataType.INTEGER,
                    format = IntegerFormat.INT_32,
                    nullable = false,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                )
            ),
            enum = emptySet()
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map generic primitive property to schema object`() {
        val givenProperty = TestClassWithGenericPrimitive::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.OBJECT,
            format = null,
            nullable = false,
            description = null,
            properties = mapOf(
                "value" to SchemaObject(
                    type = DataType.STRING,
                    format = StringFormat.NO_FORMAT,
                    nullable = false,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                )
            ),
            enum = emptySet()
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map generic nullable primitive property to schema object`() {
        val givenProperty = TestClassWithGenericNullablePrimitiveProperty::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.OBJECT,
            format = null,
            nullable = false,
            description = null,
            properties = mapOf(
                "value" to SchemaObject(
                    type = DataType.STRING,
                    format = StringFormat.NO_FORMAT,
                    nullable = true,
                    description = null,
                    properties = emptyMap(),
                    enum = emptySet()
                )
            ),
            enum = emptySet()
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map generic object property to schema object`() {
        val givenProperty = TestClassWithGenericObjectProperty::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.OBJECT,
            format = null,
            nullable = false,
            description = null,
            properties = mapOf(
                "value" to SchemaObject(
                    type = DataType.OBJECT,
                    format = null,
                    nullable = false,
                    description = null,
                    properties = mapOf(
                        "property1" to SchemaObject(
                            type = DataType.STRING,
                            format = StringFormat.NO_FORMAT,
                            nullable = false,
                            description = null,
                            properties = emptyMap(),
                            enum = emptySet()
                        ),
                        "property2" to SchemaObject(
                            type = DataType.INTEGER,
                            format = IntegerFormat.INT_32,
                            nullable = false,
                            description = null,
                            properties = emptyMap(),
                            enum = emptySet()
                        )
                    )
                )
            ),
            enum = emptySet()
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map generic nullable object property to schema object`() {
        val givenProperty = TestClassWithGenericNullableObjectProperty::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.OBJECT,
            format = null,
            nullable = false,
            description = null,
            properties = mapOf(
                "value" to SchemaObject(
                    type = DataType.OBJECT,
                    format = null,
                    nullable = true,
                    description = null,
                    properties = mapOf(
                        "property1" to SchemaObject(
                            type = DataType.STRING,
                            format = StringFormat.NO_FORMAT,
                            nullable = false,
                            description = null,
                            properties = emptyMap(),
                            enum = emptySet()
                        ),
                        "property2" to SchemaObject(
                            type = DataType.INTEGER,
                            format = IntegerFormat.INT_32,
                            nullable = false,
                            description = null,
                            properties = emptyMap(),
                            enum = emptySet()
                        )
                    )
                )
            ),
            enum = emptySet()
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map enum property to schema object`() {
        val givenProperty = TestClassWithEnum::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.ENUM,
            format = null,
            nullable = false,
            description = null,
            properties = emptyMap(),
            enum = setOf("VALUE_1", "VALUE_2")
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map nullable property to schema object`() {
        val givenProperty = TestClassWithNullableProperty::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.INTEGER,
            format = IntegerFormat.INT_32,
            nullable = true,
            description = null,
            properties = emptyMap(),
            enum = emptySet()
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    @Test
    fun `Should map property with description to schema object`() {
        val givenProperty = TestClassWithDescription::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.STRING,
            format = StringFormat.DATE,
            nullable = false,
            description = "Description",
            properties = emptyMap(),
            enum = emptySet()
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedSchemaObject)
    }

    private data class TestClassWithString(val value: String)

    private data class TestClassWithNumber(val value: Float)

    private data class TestClassWithInteger(val value: Long)

    private data class TestClassWithBoolean(val value: Boolean)

    private data class TestClassWithPrimitiveArray(val value: List<String>)

    private data class TestClassWithObjectArray(val value: List<TestObject>)

    private data class TestClassWithMap(val value: Map<String, Int>)

    private data class TestClassWithObject(val value: TestObject)

    private data class TestClassWithGenericPrimitive(val value: TestClassWithGeneric<String>)

    private data class TestClassWithGenericNullablePrimitiveProperty(val value: TestClassWithGeneric<String?>)

    private data class TestClassWithGenericObjectProperty(val value: TestClassWithGeneric<TestObject>)

    private data class TestClassWithGenericNullableObjectProperty(val value: TestClassWithGeneric<TestObject?>)

    private data class TestClassWithGeneric<T>(val value: T)

    private data class TestClassWithEnum(val value: TestEnum)

    private data class TestObject(val property1: String, val property2: Int)

    private enum class TestEnum { VALUE_1, VALUE_2 }

    private data class TestClassWithNullableProperty(val value: Int?)

    private data class TestClassWithDescription(@Description("Description") val value: LocalDate)
}
