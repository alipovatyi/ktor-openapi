package dev.arli.openapi.mapper

import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class MediaTypeMapperTest {

    private val mapper = MediaTypeMapper()

    @Test
    fun `Should map media type property to media type object`() {
        val givenProperty = TestClass::value

        val expectedMediaTypeObject = MediaTypeObject(
            schema = SchemaObject(
                type = DataType.OBJECT,
                format = null,
                nullable = false,
                properties = mapOf(
                    "property" to SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = false
                    )
                )
            ),
            example = null,
            examples = emptyMap()
        )

        assertEquals(expectedMediaTypeObject, mapper.map(givenProperty))
    }

    private data class TestClass(val value: TestObject)

    private data class TestObject(val property: String)
}
