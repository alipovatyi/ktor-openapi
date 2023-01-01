package dev.arli.openapi.generator

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.HttpSecurityScheme
import dev.arli.openapi.model.OAuth2SecurityScheme
import dev.arli.openapi.model.security.HttpSecuritySchemeType
import dev.arli.openapi.model.security.OAuth2Scope
import dev.arli.openapi.model.security.OAuth2Scopes
import dev.arli.openapi.model.security.OAuthFlowObject
import dev.arli.openapi.model.security.OAuthFlowsObject
import io.ktor.http.Url
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
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
    fun `Should convert OAuth2 security scheme to json object`() {
        val givenOAuth2SecurityScheme = OAuth2SecurityScheme(
            description = "OAuth2 security scheme",
            flows = OAuthFlowsObject(
                authorizationCode = OAuthFlowObject(
                    authorizationUrl = Url("http://localhost/auth"),
                    tokenUrl = Url("http://localhost/token"),
                    refreshUrl = Url("http://localhost/refresh-token"),
                    scopes = OAuth2Scopes(listOf(OAuth2Scope(name = "scope", description = "Scope description")))
                )
            )
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "oauth2")
            put("description", "OAuth2 security scheme")
            putJsonObject("flows") {
                putJsonObject("authorizationCode") {
                    put("authorizationUrl", "http://localhost/auth")
                    put("tokenUrl", "http://localhost/token")
                    put("refreshUrl", "http://localhost/refresh-token")
                    putJsonObject("scopes") {
                        put("scope", "Scope description")
                    }
                }
            }
        }

        val actualJsonObject = generator.generateSecuritySchemeJson(givenOAuth2SecurityScheme)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should convert reference object to json object`() {
        // TODO
    }
}
