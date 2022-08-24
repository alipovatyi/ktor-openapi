package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class TagTest {

    @Test
    fun `Should create tag`() {
        val expectedTag = Tag(
            name = "Tag",
            description = "Description",
            externalDocs = ExternalDocumentation(
                url = Url("http://localhost/external-docs")
            )
        )

        val actualTag = Tag.Builder(name = "Tag").apply {
            description = "Description"
            externalDocs(url = Url("http://localhost/external-docs"))
        }.build()

        assertThat(actualTag).isEqualTo(expectedTag)
    }

    @Test
    fun `Should create tag with default values`() {
        val expectedTag = Tag(
            name = "Tag",
            description = null,
            externalDocs = null
        )

        val actualTag = Tag.Builder(name = "Tag").build()

        assertThat(actualTag).isEqualTo(expectedTag)
    }
}
