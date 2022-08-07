package dev.arli.openapi.generator

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.HttpSecurityScheme
import dev.arli.openapi.model.security.HttpSecuritySchemeType
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.jupiter.api.Test

internal class SecuritySchemeJsonGeneratorTest {

    private val generator = SecuritySchemeJsonGenerator()

    @Test
    fun `Should convert http security scheme to json object`() {
        val givenHttpSecurityScheme = HttpSecurityScheme(
            description = "HTTP security scheme",
            scheme = HttpSecuritySchemeType.BASIC
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "http")
            put("scheme", "basic")
            put("description", "HTTP security scheme")
        }

        val actualJsonObject = generator.generateSecuritySchemeJson(givenHttpSecurityScheme)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should convert reference object to json object`() {
        // TODO
    }
}
