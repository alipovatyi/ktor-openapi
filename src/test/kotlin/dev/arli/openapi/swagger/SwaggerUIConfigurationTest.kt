package dev.arli.openapi.swagger

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class SwaggerUIConfigurationTest {

    @Test
    fun `Should return swagger ui configuration with default values`() {
        val expectedSwaggerUIConfiguration = SwaggerUIConfiguration(
            path = "/",
            webjarsPath = "webjars",
            specificationFileName = "openapi.json"
        )

        val actualSwaggerUIConfiguration = SwaggerUIConfiguration(
            specificationFileName = "openapi.json"
        )

        assertThat(actualSwaggerUIConfiguration).isEqualTo(expectedSwaggerUIConfiguration)
    }

    @ParameterizedTest
    @MethodSource
    fun `Should return correct OAuth2 redirect path`(givenPath: String, expectedOAuth2RedirectPath: String) {
        val givenSwaggerUIConfiguration = SwaggerUIConfiguration(
            path = givenPath,
            specificationFileName = "openapi.json"
        )

        assertThat(givenSwaggerUIConfiguration.oauth2RedirectPath).isEqualTo(expectedOAuth2RedirectPath)
    }

    private companion object {

        @JvmStatic
        fun `Should return correct OAuth2 redirect path`() = listOf(
            arguments("", "/oauth2-redirect"),
            arguments("/", "/oauth2-redirect"),
            arguments("documentation", "/documentation/oauth2-redirect"),
            arguments("/documentation", "/documentation/oauth2-redirect")
        )
    }
}
