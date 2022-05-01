package dev.arli.openapi.generator

import dev.arli.openapi.model.ExternalDocumentationObject
import io.ktor.http.Url
import kotlin.test.assertEquals
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.jupiter.api.Test

internal class ExternalDocumentationJsonGeneratorTest {

    private val generator = ExternalDocumentationJsonGenerator()

    @Test
    fun `Should convert external documentation to json object`() {
        val givenExternalDocumentation = ExternalDocumentationObject(
            url = Url("http://localhost/external-docs"),
            description = "External documentation description"
        )
        val expectedJsonObject = buildJsonObject {
            put("url", "http://localhost/external-docs")
            put("description", "External documentation description")
        }

        assertEquals(expectedJsonObject, generator.generateExternalDocumentationJson(givenExternalDocumentation))
    }

    @Test
    fun `Should exclude null values`() {
        val givenExternalDocumentation = ExternalDocumentationObject(
            url = Url("http://localhost/external-docs")
        )
        val expectedJsonObject = buildJsonObject {
            put("url", "http://localhost/external-docs")
        }

        assertEquals(expectedJsonObject, generator.generateExternalDocumentationJson(givenExternalDocumentation))
    }
}
