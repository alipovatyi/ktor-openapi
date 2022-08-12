package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.property.ArrayProperty
import dev.arli.openapi.model.property.BooleanProperty
import dev.arli.openapi.model.property.EnumProperty
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.IntegerProperty
import dev.arli.openapi.model.property.MapProperty
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

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedStringProperty)
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

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedIntegerProperty)
    }

    @Test
    fun `Should map property to boolean property`() {
        val givenProperty = TestClassWithBoolean::boolean

        val expectedBooleanProperty = BooleanProperty(
            name = "boolean",
            description = null,
            nullable = false
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedBooleanProperty)
    }

    @Test
    fun `Should map property to array property`() {
        val givenProperty = TestClassWithList::list

        val expectedArrayProperty = ArrayProperty(
            name = "list",
            description = null,
            nullable = false
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedArrayProperty)
    }

    @Test
    fun `Should map property to map property`() {
        val givenProperty = TestClassWithMap::map

        val expectedMapProperty = MapProperty(
            name = "map",
            description = null,
            nullable = false
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedMapProperty)
    }

    @Test
    fun `Should map property to object property`() {
        val givenProperty = TestClassWithObject::obj

        val expectedObjectProperty = ObjectProperty(
            name = "obj",
            description = null,
            nullable = false
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedObjectProperty)
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

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedEnumProperty)
    }

    private data class TestClassWithString(val string: String)

    private data class TestClassWithDouble(val double: Double)

    private data class TestClassWithInt(val int: Int)

    private data class TestClassWithBoolean(val boolean: Boolean)

    private data class TestClassWithList(val list: List<Any>)

    private data class TestClassWithMap(val map: Map<String, Int>)

    private data class TestClassWithObject(val obj: TestObject)

    private data class TestClassWithEnum(val enum: TestEnum)

    private enum class TestEnum { VALUE }

    private data class TestObject(val property1: String, val property2: Int)
}
