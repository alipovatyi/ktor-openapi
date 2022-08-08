package dev.arli.openapi.mapper

import dev.arli.openapi.model.property.ArrayProperty
import dev.arli.openapi.model.property.BooleanProperty
import dev.arli.openapi.model.property.EnumProperty
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.IntegerProperty
import dev.arli.openapi.model.property.NumberFormat
import dev.arli.openapi.model.property.NumberProperty
import dev.arli.openapi.model.property.ObjectProperty
import dev.arli.openapi.model.property.StringFormat
import dev.arli.openapi.model.property.StringProperty
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PropertyMapperTest {

    private val mapper = PropertyMapper()

    @Test
    fun `Should map property to string property`() {
        val givenProperty = TestClassWithString::string

        val expectedStringProperty = StringProperty(
            name = "string",
            description = null,
            nullable = false,
            format = StringFormat.NO_FORMAT
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to number property`() {
        val givenProperty = TestClassWithDouble::double

        val expectedNumberProperty = NumberProperty(
            name = "double",
            description = null,
            nullable = false,
            format = NumberFormat.DOUBLE
        )

        assertEquals(expectedNumberProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to integer property`() {
        val givenProperty = TestClassWithInt::int

        val expectedIntegerProperty = IntegerProperty(
            name = "int",
            description = null,
            nullable = false,
            format = IntegerFormat.INT_32
        )

        assertEquals(expectedIntegerProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to boolean property`() {
        val givenProperty = TestClassWithBoolean::boolean

        val expectedBooleanProperty = BooleanProperty(
            name = "boolean",
            description = null,
            nullable = false
        )

        assertEquals(expectedBooleanProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to array property`() {
        val givenProperty = TestClassWithList::list

        val expectedArrayProperty = ArrayProperty(
            name = "list",
            description = null,
            nullable = false
        )

        assertEquals(expectedArrayProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to object property`() {
        val givenProperty = TestClassWithMap::map

        val expectedObjectProperty = ObjectProperty(
            name = "map",
            description = null,
            nullable = false
        )

        assertEquals(expectedObjectProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to enum property`() {
        val givenProperty = TestClassWithEnum::enum

        val expectedEnumProperty = EnumProperty(
            name = "enum",
            description = null,
            nullable = false,
            values = setOf("VALUE")
        )

        assertEquals(expectedEnumProperty, mapper.map(givenProperty))
    }

    private data class TestClassWithString(val string: String)

    private data class TestClassWithDouble(val double: Double)

    private data class TestClassWithInt(val int: Int)

    private data class TestClassWithBoolean(val boolean: Boolean)

    private data class TestClassWithList(val list: List<Any>)

    private data class TestClassWithMap(val map: Map<String, Any>)

    private data class TestClassWithEnum(val enum: TestEnum)

    private enum class TestEnum { VALUE }
}
