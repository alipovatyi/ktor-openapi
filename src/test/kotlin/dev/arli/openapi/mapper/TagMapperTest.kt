package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.ExternalDocumentationObject
import dev.arli.openapi.model.Tag
import dev.arli.openapi.model.TagObject
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class TagMapperTest {

    private val mapper = TagMapper()

    @Test
    fun `Should map tag to tag object`() {
        val givenTag = Tag(
            name = "Tag",
            description = "Description",
            externalDocs = ExternalDocumentationObject(
                url = Url("http://localhost/external-docs")
            )
        )

        val expectedTagObject = TagObject(
            name = "Tag",
            description = "Description",
            externalDocs = ExternalDocumentationObject(
                url = Url("http://localhost/external-docs")
            )
        )

        assertThat(mapper.map(givenTag)).isEqualTo(expectedTagObject)
    }
}
