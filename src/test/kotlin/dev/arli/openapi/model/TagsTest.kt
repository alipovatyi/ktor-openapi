package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class TagsTest {

    @Test
    fun `Should create tags`() {
        val expectedTags = Tags(
            tags = listOf(
                Tag(
                    name = "Tag 1",
                    description = "Tag description",
                    externalDocs = null
                ),
                Tag(
                    name = "Tag 2",
                    description = null,
                    externalDocs = null
                )
            )
        )

        val actualTags = Tags.Builder().apply {
            tag(name = "Tag 1") { description = "Tag description" }
            tag(name = "Tag 2")
        }.build()

        assertThat(actualTags).isEqualTo(expectedTags)
    }

    @Test
    fun `Should throw an exception if tag name is not unique`() {
        assertFailsWith<IllegalArgumentException> {
            Tags.Builder().apply {
                tag(name = "Tag")
                tag(name = "Tag")
            }.build()
        }
    }
}
