package dev.arli.openapi.generator.security

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.security.OAuth2Scopes
import dev.arli.openapi.model.security.OAuthFlowObject
import dev.arli.openapi.model.security.OAuthFlowsObject
import io.ktor.http.Url
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class OAuthFlowsJsonGeneratorTest {

    private val generator = OAuthFlowsJsonGenerator()

    @Test
    fun `Should convert OAuth flows object to json object`() {
        val givenOAuthFlowsObject = OAuthFlowsObject(
            authorizationCode = OAuthFlowObject(
                authorizationUrl = Url("http://localhost/auth"),
                tokenUrl = Url("http://localhost/token"),
                refreshUrl = null,
                scopes = OAuth2Scopes()
            )
        )

        val expectedJsonObject = buildJsonObject {
            putJsonObject("authorizationCode") {
                put("authorizationUrl", "http://localhost/auth")
                put("tokenUrl", "http://localhost/token")
                putJsonObject("scopes") {}
            }
        }

        val actualJsonObject = generator.generateOAuthFlowsJson(givenOAuthFlowsObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should exclude null values`() {
        val givenOAuthFlowsObject = OAuthFlowsObject(
            authorizationCode = null
        )

        val expectedJsonObject = buildJsonObject {}

        val actualJsonObject = generator.generateOAuthFlowsJson(givenOAuthFlowsObject)

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }
}
