package dev.arli.openapi.model

import io.ktor.http.Url
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ExternalDocumentationObjectTest {

    @Test
    fun `Should return external documentation with default values`() {
        val expectedExternalDocumentationObject = ExternalDocumentationObject(
            url = Url("http://localhost/external-docs"),
            description = null
        )
        val actualExternalDocumentation = ExternalDocumentationObject(
            url = Url("http://localhost/external-docs")
        )

        assertEquals(expectedExternalDocumentationObject, actualExternalDocumentation)
    }
}
