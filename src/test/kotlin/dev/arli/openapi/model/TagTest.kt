package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class TagTest {

    @Test
    fun `Should create tag`() {
        val expectedTag = Tag(
            name = "Tag",
            description = "Description",
            externalDocs = null
        )

        val actualTag = Tag.Builder(name = "Tag").apply {
            description = "Description"
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
