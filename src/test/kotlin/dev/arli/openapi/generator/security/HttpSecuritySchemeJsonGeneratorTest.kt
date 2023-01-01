package dev.arli.openapi.generator.security

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.HttpSecurityScheme
import dev.arli.openapi.model.security.HttpSecuritySchemeType
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.jupiter.api.Test

internal class HttpSecuritySchemeJsonGeneratorTest {

    private val generator = HttpSecuritySchemeJsonGenerator()

    @Test
    fun `Should convert basic http security scheme to json object`() {
        val givenHttpSecurityScheme = HttpSecurityScheme(
            description = "Description",
            scheme = HttpSecuritySchemeType.BASIC
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "http")
            put("scheme", "basic")
            put("description", "Description")
        }

        val actualJsonObject = generator.generateHttpSecuritySchemeJson(givenHttpSecurityScheme)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should convert bearer http security scheme to json object`() {
        val givenHttpSecurityScheme = HttpSecurityScheme(
            description = "Description",
            scheme = HttpSecuritySchemeType.BEARER,
            bearerFormat = "Bearer format"
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "http")
            put("scheme", "bearer")
            put("description", "Description")
            put("bearerFormat", "Bearer format")
        }

        val actualJsonObject = generator.generateHttpSecuritySchemeJson(givenHttpSecurityScheme)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should exclude null values`() {
        val givenHttpSecurityScheme = HttpSecurityScheme(
            description = null,
            scheme = HttpSecuritySchemeType.BEARER,
            bearerFormat = null
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "http")
            put("scheme", "bearer")
        }

        val actualJsonObject = generator.generateHttpSecuritySchemeJson(givenHttpSecurityScheme)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }
}
