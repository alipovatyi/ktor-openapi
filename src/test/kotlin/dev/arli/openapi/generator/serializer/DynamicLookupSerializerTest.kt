package dev.arli.openapi.generator.serializer

import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.jupiter.api.Test
import kotlinx.serialization.Serializable

internal class DynamicLookupSerializerTest {

    private val serializer = DynamicLookupSerializer()

    @Test
    fun `Should serialize dynamic types`() {
        val givenObject: Any = TestClass(value1 = "Value", value2 = 1)

        val expectedJsonObject = buildJsonObject {
            put("value1", "Value")
            put("value_2", 1)
        }

        val actualJsonObject = Json.encodeToJsonElement(serializer, givenObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Serializable
    private data class TestClass(
        val value1: String,
        @SerialName("value_2") val value2: Int
    )
}
