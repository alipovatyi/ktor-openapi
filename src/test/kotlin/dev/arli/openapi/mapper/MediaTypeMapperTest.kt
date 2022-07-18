package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.Example.Companion.example
import dev.arli.openapi.model.ExampleObject
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.Response
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import io.ktor.http.HttpStatusCode
import org.junit.jupiter.api.Test

internal class MediaTypeMapperTest {

    private val mapper = MediaTypeMapper()

    @Test
    fun `Should map media type property to media type object`() {
        val givenProperty = TestClass::value
        val givenResponse = Response(
            responseClass = TestObject::class,
            statusCode = HttpStatusCode.OK,
            example = TestExample(1),
            examples = mapOf(
                "example-1" to example(2),
                "example-2" to example(3)
            )
        )

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
            example = TestExample(1),
            examples = mapOf(
                "example-1" to ExampleObject(2),
                "example-2" to ExampleObject(3)
            )
        )

        val actualMediaTypeObject = mapper.map(givenProperty, givenResponse)

        assertThat(actualMediaTypeObject).isEqualTo(expectedMediaTypeObject)
    }

    private data class TestClass(val value: TestObject)

    private data class TestObject(val property: String)

    private data class TestExample(val value: Int)
}
