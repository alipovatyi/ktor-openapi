package dev.arli.openapi.generator.security

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.security.OAuth2Scope
import dev.arli.openapi.model.security.OAuth2Scopes
import dev.arli.openapi.model.security.OAuthFlowObject
import io.ktor.http.Url
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class OAuthAuthorizationCodeFlowJsonGeneratorTest {

    private val generator = OAuthAuthorizationCodeFlowJsonGenerator()

    @Test
    fun `Should convert OAuth authorization code flow object to json object`() {
        val givenOAuth2AuthorizationCodeFlowObject = OAuthFlowObject(
            authorizationUrl = Url("http://localhost/auth"),
            tokenUrl = Url("http://localhost/token"),
            refreshUrl = Url("http://localhost/refresh-token"),
            scopes = OAuth2Scopes(
                listOf(
                    OAuth2Scope(name = "scope-1", description = "Scope description"),
                    OAuth2Scope(name = "scope-2", description = null)
                )
            )
        )

        val expectedJsonObject = buildJsonObject {
            put("authorizationUrl", "http://localhost/auth")
            put("tokenUrl", "http://localhost/token")
            put("refreshUrl", "http://localhost/refresh-token")
            putJsonObject("scopes") {
                put("scope-1", "Scope description")
                put("scope-2", "")
            }
        }

        val actualJsonObject = generator.generateOAuthAuthorizationCodeFlowJson(givenOAuth2AuthorizationCodeFlowObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should exclude null values`() {
        val givenOAuth2AuthorizationCodeFlowObject = OAuthFlowObject(
            authorizationUrl = Url("http://localhost/auth"),
            tokenUrl = Url("http://localhost/token"),
            refreshUrl = null,
            scopes = OAuth2Scopes()
        )

        val expectedJsonObject = buildJsonObject {
            put("authorizationUrl", "http://localhost/auth")
            put("tokenUrl", "http://localhost/token")
            putJsonObject("scopes") {}
        }

        val actualJsonObject = generator.generateOAuthAuthorizationCodeFlowJson(givenOAuth2AuthorizationCodeFlowObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should throw exception if authorization url is null`() {
        val givenOAuth2AuthorizationCodeFlowObject = OAuthFlowObject(
            authorizationUrl = null,
            tokenUrl = Url("http://localhost/token"),
            refreshUrl = null,
            scopes = OAuth2Scopes()
        )

        assertFailsWith<IllegalArgumentException> {
            generator.generateOAuthAuthorizationCodeFlowJson(givenOAuth2AuthorizationCodeFlowObject)
        }
    }

    @Test
    fun `Should throw exception if token url is null`() {
        val givenOAuth2AuthorizationCodeFlowObject = OAuthFlowObject(
            authorizationUrl = Url("http://localhost/auth"),
            tokenUrl = null,
            refreshUrl = null,
            scopes = OAuth2Scopes()
        )

        assertFailsWith<IllegalArgumentException> {
            generator.generateOAuthAuthorizationCodeFlowJson(givenOAuth2AuthorizationCodeFlowObject)
        }
    }
}
