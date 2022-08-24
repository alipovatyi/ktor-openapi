package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class ExternalDocumentationTest {

    @Test
    fun `Should create external documentation`() {
        val expectedExternalDocumentation = ExternalDocumentation(
            url = Url("http://localhost/external-docs"),
            description = "External documentation description"
        )

        val actualExternalDocumentation = ExternalDocumentation.Builder(
            url = Url("http://localhost/external-docs")
        ).apply {
            description = "External documentation description"
        }.build()

        assertThat(actualExternalDocumentation).isEqualTo(expectedExternalDocumentation)
    }

    @Test
    fun `Should create external documentation with default values`() {
        val expectedExternalDocumentation = ExternalDocumentation(
            url = Url("http://localhost/external-docs"),
            description = null
        )

        val actualExternalDocumentation = ExternalDocumentation.Builder(
            url = Url("http://localhost/external-docs")
        ).build()

        assertThat(actualExternalDocumentation).isEqualTo(expectedExternalDocumentation)
    }
}
