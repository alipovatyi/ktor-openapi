package dev.arli.openapi.model.security

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class OAuth2ScopesTest {

    @Test
    fun `Should create OAuth2 scopes`() {
        val expectedOAuth2Scopes = OAuth2Scopes(
            scopes = listOf(
                OAuth2Scope(
                    name = "scope-1",
                    description = "Scope 1"
                ),
                OAuth2Scope(
                    name = "scope-2",
                    description = null
                )
            )
        )

        val actualOAuth2Scopes = OAuth2Scopes.Builder().apply {
            scope(name = "scope-1") {
                description = "Scope 1"
            }
            scope(name = "scope-2")
        }.build()

        assertThat(actualOAuth2Scopes).isEqualTo(expectedOAuth2Scopes)
    }
}
