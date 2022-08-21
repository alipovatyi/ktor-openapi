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

internal class OAuthImplicitFlowJsonGeneratorTest {

    private val generator = OAuthImplicitFlowJsonGenerator()

    @Test
    fun `Should convert OAuth implicit flow object to json object`() {
        val givenOAuth2ImplicitFlowObject = OAuthFlowObject(
            authorizationUrl = Url("http://localhost/auth"),
            tokenUrl = null,
            refreshUrl = null,
            scopes = OAuth2Scopes(
                listOf(
                    OAuth2Scope(name = "scope-1", description = "Scope description"),
                    OAuth2Scope(name = "scope-2", description = null)
                )
            )
        )

        val expectedJsonObject = buildJsonObject {
            put("authorizationUrl", "http://localhost/auth")
            putJsonObject("scopes") {
                put("scope-1", "Scope description")
                put("scope-2", "")
            }
        }

        val actualJsonObject = generator.generateOAuthImplicitFlowJson(givenOAuth2ImplicitFlowObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should exclude null values`() {
        val givenOAuth2ImplicitFlowObject = OAuthFlowObject(
            authorizationUrl = Url("http://localhost/auth"),
            tokenUrl = null,
            refreshUrl = null,
            scopes = OAuth2Scopes()
        )

        val expectedJsonObject = buildJsonObject {
            put("authorizationUrl", "http://localhost/auth")
            putJsonObject("scopes") {}
        }

        val actualJsonObject = generator.generateOAuthImplicitFlowJson(givenOAuth2ImplicitFlowObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should throw exception if authorization url is null`() {
        val givenOAuth2ImplicitFlowObject = OAuthFlowObject(
            authorizationUrl = null,
            tokenUrl = null,
            refreshUrl = null,
            scopes = OAuth2Scopes()
        )

        assertFailsWith<IllegalArgumentException> {
            generator.generateOAuthImplicitFlowJson(givenOAuth2ImplicitFlowObject)
        }
    }
}
