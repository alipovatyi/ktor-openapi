package dev.arli.openapi.generator

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.ExternalDocumentationObject
import dev.arli.openapi.model.TagObject
import io.ktor.http.Url
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class TagJsonGeneratorTest {

    private val generator = TagJsonGenerator()

    @Test
    fun `Should convert tag object to json object`() {
        val givenTagObject = TagObject(
            name = "Tag",
            description = "Description",
            externalDocs = ExternalDocumentationObject(
                url = Url("http://localhost/external-docs")
            )
        )

        val expectedJsonObject = buildJsonObject {
            put("name", "Tag")
            put("description", "Description")
            putJsonObject("externalDocs") {
                put("url", "http://localhost/external-docs")
            }
        }

        val actualJsonObject = generator.generateTagJson(givenTagObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should exclude null values`() {
        val givenTagObject = TagObject(
            name = "Tag",
            description = null,
            externalDocs = null
        )

        val expectedJsonObject = buildJsonObject {
            put("name", "Tag")
        }

        val actualJsonObject = generator.generateTagJson(givenTagObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }
}
