package dev.arli.openapi.mapper

import dev.arli.openapi.model.ArrayProperty
import dev.arli.openapi.model.BooleanProperty
import dev.arli.openapi.model.IntegerProperty
import dev.arli.openapi.model.NumberProperty
import dev.arli.openapi.model.ObjectProperty
import dev.arli.openapi.model.StringProperty
import dev.arli.openapi.util.IntegerFormat
import dev.arli.openapi.util.NumberFormat
import dev.arli.openapi.util.StringFormat
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

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

        val expectedStringProperty = NumberProperty(
            name = "double",
            description = null,
            nullable = false,
            format = NumberFormat.DOUBLE
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to integer property`() {
        val givenProperty = TestClassWithInt::int

        val expectedStringProperty = IntegerProperty(
            name = "int",
            description = null,
            nullable = false,
            format = IntegerFormat.INT_32
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to boolean property`() {
        val givenProperty = TestClassWithBoolean::boolean

        val expectedStringProperty = BooleanProperty(
            name = "boolean",
            description = null,
            nullable = false
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to array property`() {
        val givenProperty = TestClassWithList::list

        val expectedStringProperty = ArrayProperty(
            name = "list",
            description = null,
            nullable = false
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to object property`() {
        val givenProperty = TestClassWithMap::map

        val expectedStringProperty = ObjectProperty(
            name = "map",
            description = null,
            nullable = false
        )

        assertEquals(expectedStringProperty, mapper.map(givenProperty))
    }

    private data class TestClassWithString(val string: String)

    private data class TestClassWithDouble(val double: Double)

    private data class TestClassWithInt(val int: Int)

    private data class TestClassWithBoolean(val boolean: Boolean)

    private data class TestClassWithList(val list: List<Any>)

    private data class TestClassWithMap(val map: Map<String, Any>)
}
