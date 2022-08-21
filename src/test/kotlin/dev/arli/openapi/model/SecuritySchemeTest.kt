package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.security.HttpSecuritySchemeType
import dev.arli.openapi.model.security.OAuthFlowsObject
import dev.arli.openapi.model.security.SecuritySchemeType
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class SecuritySchemeTest {

    @ParameterizedTest
    @MethodSource
    fun `Should set correct security scheme type`(
        givenSecuritySchemeObject: SecuritySchemeObject,
        expectedSecuritySchemeType: SecuritySchemeType
    ) {
        assertThat(givenSecuritySchemeObject.type).isEqualTo(expectedSecuritySchemeType)
    }

    private companion object {

        @JvmStatic
        fun `Should set correct security scheme type`() = listOf(
            arguments(HttpSecurityScheme(null, HttpSecuritySchemeType.BASIC), SecuritySchemeType.HTTP),
            arguments(OAuth2SecurityScheme(null, OAuthFlowsObject(null)), SecuritySchemeType.OAUTH2)
        )
    }
}
