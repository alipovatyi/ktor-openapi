package dev.arli.openapi.parser

import kotlin.reflect.full.declaredMemberProperties
import kotlin.test.assertEquals
import kotlinx.serialization.SerialName
import org.junit.jupiter.api.Test

internal class PropertyNameParserTest {

    private val parser = PropertyNameParser()

    @Test
    fun `Should return property name if SerialName annotation is not found`() {
        val property = TestClass::class.declaredMemberProperties.first()

        val expectedName = "value"

        assertEquals(expectedName, parser.parse(property))
    }

    @Test
    fun `Should return value from SerialName annotation`() {
        val property = TestClassWithCustomSerialName::class.declaredMemberProperties.first()

        val expectedName = "custom-value"

        assertEquals(expectedName, parser.parse(property))
    }

    private data class TestClass(val value: String)

    private data class TestClassWithCustomSerialName(
        @SerialName("custom-value") val value: String
    )
}
