package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.NumberProperty
import dev.arli.openapi.util.NumberFormat
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.serialization.SerialName
import org.junit.jupiter.api.Test

internal class NumberPropertyMapperTest {

    private val mapper = NumberPropertyMapper()

    @Test
    fun `Should map Float property to number property`() {
        val givenProperty = TestClassWithFloat::value

        val expectedNumberProperty = NumberProperty(
            name = "value",
            description = null,
            nullable = false,
            format = NumberFormat.FLOAT
        )

        assertEquals(expectedNumberProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Float property to nullable number property`() {
        val givenProperty = TestClassWithNullableFloat::value

        val expectedNumberProperty = NumberProperty(
            name = "value",
            description = null,
            nullable = true,
            format = NumberFormat.FLOAT
        )

        assertEquals(expectedNumberProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map Double property to number property`() {
        val givenProperty = TestClassWithDouble::value

        val expectedNumberProperty = NumberProperty(
            name = "value",
            description = null,
            nullable = false,
            format = NumberFormat.DOUBLE
        )

        assertEquals(expectedNumberProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable Double property to nullable number property`() {
        val givenProperty = TestClassWithNullableDouble::value

        val expectedNumberProperty = NumberProperty(
            name = "value",
            description = null,
            nullable = true,
            format = NumberFormat.DOUBLE
        )

        assertEquals(expectedNumberProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map BigDecimal property to number property`() {
        val givenProperty = TestClassWithBigDecimal::value

        val expectedNumberProperty = NumberProperty(
            name = "value",
            description = null,
            nullable = false,
            format = NumberFormat.NO_FORMAT
        )

        assertEquals(expectedNumberProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map nullable BigDecimal to nullable number property`() {
        val givenProperty = TestClassWithNullableBigDecimal::value

        val expectedNumberProperty = NumberProperty(
            name = "value",
            description = null,
            nullable = true,
            format = NumberFormat.NO_FORMAT
        )

        assertEquals(expectedNumberProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to number property with custom name`() {
        val givenProperty = TestClassWithCustomSerialName::value

        val expectedNumberProperty = NumberProperty(
            name = "custom-value",
            description = null,
            nullable = false,
            format = NumberFormat.FLOAT
        )

        assertEquals(expectedNumberProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should map property to number property with description`() {
        val givenProperty = TestClassWithDescription::value

        val expectedNumberProperty = NumberProperty(
            name = "value",
            description = "Description",
            nullable = false,
            format = NumberFormat.FLOAT
        )

        assertEquals(expectedNumberProperty, mapper.map(givenProperty))
    }

    @Test
    fun `Should throw exception for unsupported number type`() {
        val givenProperty = TestClassWithInvalidType::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithFloat(val value: Float)

    private data class TestClassWithNullableFloat(val value: Float?)

    private data class TestClassWithDouble(val value: Double)

    private data class TestClassWithNullableDouble(val value: Double?)

    private data class TestClassWithBigDecimal(val value: BigDecimal)

    private data class TestClassWithNullableBigDecimal(val value: BigDecimal?)

    private data class TestClassWithCustomSerialName(@SerialName("custom-value") val value: Float)

    private data class TestClassWithDescription(@Description("Description") val value: Float)

    private data class TestClassWithInvalidType(val value: Int)
}
