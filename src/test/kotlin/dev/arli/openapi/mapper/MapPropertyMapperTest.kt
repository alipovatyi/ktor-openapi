package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.annotation.Description
import dev.arli.openapi.model.property.MapProperty
import kotlinx.serialization.SerialName
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class MapPropertyMapperTest {

    private val mapper = MapPropertyMapper()

    @Test
    fun `Should map Map property to map property`() {
        val givenProperty = TestClassWithMap::value

        val expectedMapProperty = MapProperty(
            name = "value",
            description = null,
            nullable = false
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedMapProperty)
    }

    @Test
    fun `Should map nullable Map property to map property`() {
        val givenProperty = TestClassWithNullableMap::value

        val expectedMapProperty = MapProperty(
            name = "value",
            description = null,
            nullable = true
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedMapProperty)
    }

    @Test
    fun `Should map property to map property with custom name`() {
        val givenProperty = TestClassWithCustomSerialName::value

        val expectedMapProperty = MapProperty(
            name = "custom-value",
            description = null,
            nullable = false
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedMapProperty)
    }

    @Test
    fun `Should map property to map property with description`() {
        val givenProperty = TestClassWithDescription::value

        val expectedMapProperty = MapProperty(
            name = "value",
            description = "Description",
            nullable = false
        )

        assertThat(mapper.map(givenProperty)).isEqualTo(expectedMapProperty)
    }

    @Test
    fun `Should throw exception for unsupported map type`() {
        val givenProperty = TestClassWithInvalidType::value

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenProperty)
        }
    }

    private data class TestClassWithMap(val value: Map<String, Int>)

    private data class TestClassWithNullableMap(val value: Map<String, Int>?)

    private data class TestClassWithCustomSerialName(@SerialName("custom-value") val value: Map<String, Int>)

    private data class TestClassWithDescription(@Description("Description") val value: Map<String, Int>)

    private data class TestClassWithInvalidType(val value: List<Any>)
}
