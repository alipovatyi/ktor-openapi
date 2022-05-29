package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.NumberFormat
import dev.arli.openapi.model.property.StringFormat
import kotlin.test.assertEquals
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

        assertEquals(expectedSchemaObject, mapper.map(givenProperty))
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

        assertEquals(expectedSchemaObject, mapper.map(givenProperty))
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

        assertEquals(expectedSchemaObject, mapper.map(givenProperty))
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

        assertEquals(expectedSchemaObject, mapper.map(givenProperty))
    }

    @Test
    fun `Should map array property to schema object`() {
        val givenProperty = TestClassWithArray::value

        val expectedSchemaObject = SchemaObject(
            type = DataType.ARRAY,
            format = null,
            nullable = false,
            description = null,
            properties = emptyMap(),
            enum = emptySet()
        )

        assertEquals(expectedSchemaObject, mapper.map(givenProperty))
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

        assertEquals(expectedSchemaObject, mapper.map(givenProperty))
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

        assertEquals(expectedSchemaObject, mapper.map(givenProperty))
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

        assertEquals(expectedSchemaObject, mapper.map(givenProperty))
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

        assertEquals(expectedSchemaObject, mapper.map(givenProperty))
    }

    private data class TestClassWithString(val value: String)

    private data class TestClassWithNumber(val value: Float)

    private data class TestClassWithInteger(val value: Long)

    private data class TestClassWithBoolean(val value: Boolean)

    private data class TestClassWithArray(val value: List<Any>)

    private data class TestClassWithObject(val value: TestObject)

    private data class TestClassWithEnum(val value: TestEnum)

    private data class TestObject(val property1: String, val property2: Int)

    private enum class TestEnum { VALUE_1, VALUE_2 }

    private data class TestClassWithNullableProperty(val value: Int?)

    private data class TestClassWithDescription(@Description("Description") val value: LocalDate)
}
