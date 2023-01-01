package dev.arli.openapi.model.security

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class OAuth2ScopeTest {

    @Test
    fun `Should create OAuth2 scope`() {
        val expectedOAuth2Scope = OAuth2Scope(
            name = "scope",
            description = "Description"
        )

        val actualOAuth2Scope = OAuth2Scope.Builder(name = "scope").apply {
            description = "Description"
        }.build()

        assertThat(actualOAuth2Scope).isEqualTo(expectedOAuth2Scope)
    }

    @Test
    fun `Should create OAuth2 scope with default values`() {
        val expectedOAuth2Scope = OAuth2Scope(
            name = "scope",
            description = null
        )

        val actualOAuth2Scope = OAuth2Scope.Builder(name = "scope").build()

        assertThat(actualOAuth2Scope).isEqualTo(expectedOAuth2Scope)
    }
}
