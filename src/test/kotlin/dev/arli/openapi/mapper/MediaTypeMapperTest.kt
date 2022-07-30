package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.ExampleObject
import dev.arli.openapi.model.Examples
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.example
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.jupiter.api.Test

internal class MediaTypeMapperTest {

    private val json = Json
    private val mapper = MediaTypeMapper()

    @Test
    fun `Should map media type property to media type object`() {
        val givenParams = MediaTypeMapper.Params(
            kProperty = TestResponse::content,
            example = TestContent(1),
            exampleJson = JsonPrimitive(1),
            examples = Examples.Builder<TestContent>(json = json).apply {
                example(name = "example-2", value = TestContent(2))
                example(name = "example-3", value = TestContent(3))
            }.build()
        )

        val expectedMediaTypeObject = MediaTypeObject(
            schema = SchemaObject(
                type = DataType.OBJECT,
                format = null,
                nullable = false,
                properties = mapOf(
                    "value" to SchemaObject(
                        type = DataType.INTEGER,
                        format = IntegerFormat.INT_32,
                        nullable = false
                    )
                )
            ),
            example = TestContent(1),
            exampleJson = JsonPrimitive(1),
            examples = mapOf(
                "example-2" to ExampleObject(
                    value = TestContent(2),
                    valueJson = buildJsonObject { put("value", 2) }
                ),
                "example-3" to ExampleObject(
                    value = TestContent(3),
                    valueJson = buildJsonObject { put("value", 3) }
                )
            )
        )

        assertThat(mapper.map(givenParams)).isEqualTo(expectedMediaTypeObject)
    }

    private data class TestResponse(val content: TestContent)

    @Serializable
    private data class TestContent(val value: Int)
}
