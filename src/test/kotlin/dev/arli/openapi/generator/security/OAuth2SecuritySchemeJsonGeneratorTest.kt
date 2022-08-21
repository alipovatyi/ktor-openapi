package dev.arli.openapi.generator.security

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.OAuth2SecurityScheme
import dev.arli.openapi.model.security.OAuth2Scopes
import dev.arli.openapi.model.security.OAuthFlowObject
import dev.arli.openapi.model.security.OAuthFlowsObject
import io.ktor.http.Url
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class OAuth2SecuritySchemeJsonGeneratorTest {

    private val generator = OAuth2SecuritySchemeJsonGenerator()

    @Test
    fun `Should map OAuth2 security scheme to json object`() {
        val givenOAuth2SecurityScheme = OAuth2SecurityScheme(
            description = "OAuth2 description",
            flows = OAuthFlowsObject(
                authorizationCode = OAuthFlowObject(
                    authorizationUrl = Url("http://localhost/auth"),
                    tokenUrl = Url("http://localhost/token"),
                    refreshUrl = null,
                    scopes = OAuth2Scopes()
                )
            )
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "oauth2")
            put("description", "OAuth2 description")
            putJsonObject("flows") {
                putJsonObject("authorizationCode") {
                    put("authorizationUrl", "http://localhost/auth")
                    put("tokenUrl", "http://localhost/token")
                    putJsonObject("scopes") {}
                }
            }
        }

        val actualJsonObject = generator.generateOAuth2SecuritySchemeJson(givenOAuth2SecurityScheme)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should exclude null values`() {
        val givenOAuth2SecurityScheme = OAuth2SecurityScheme(
            description = null,
            flows = OAuthFlowsObject(
                authorizationCode = null
            )
        )

        val expectedJsonObject = buildJsonObject {
            put("type", "oauth2")
            putJsonObject("flows") {}
        }

        val actualJsonObject = generator.generateOAuth2SecuritySchemeJson(givenOAuth2SecurityScheme)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }
}
