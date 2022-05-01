package dev.arli.openapi.model

import io.ktor.http.Url
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

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
