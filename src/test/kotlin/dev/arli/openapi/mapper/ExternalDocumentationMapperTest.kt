package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.ExternalDocumentation
import dev.arli.openapi.model.ExternalDocumentationObject
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class ExternalDocumentationMapperTest {

    private val mapper = ExternalDocumentationMapper()

    @Test
    fun `Should map external documentation to external documentation object`() {
        val givenExternalDocumentation = ExternalDocumentation(
            url = Url("http://localhost/external-docs"),
            description = "External documentation description"
        )

        val expectedExternalDocumentationObject = ExternalDocumentationObject(
            url = Url("http://localhost/external-docs"),
            description = "External documentation description"
        )

        val actualExternalDocumentationObject = mapper.map(givenExternalDocumentation)

        assertThat(actualExternalDocumentationObject).isEqualTo(expectedExternalDocumentationObject)
    }
}
