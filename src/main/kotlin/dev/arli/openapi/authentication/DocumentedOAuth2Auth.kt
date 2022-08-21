package dev.arli.openapi.authentication

import dev.arli.openapi.OpenAPIGen
import dev.arli.openapi.model.OAuth2SecurityScheme
import dev.arli.openapi.model.security.OAuth2Scopes
import dev.arli.openapi.model.security.OAuth2ScopesBuilder
import dev.arli.openapi.model.security.OAuthFlowObject
import dev.arli.openapi.model.security.OAuthFlowsObject
import io.ktor.http.Url
import io.ktor.server.auth.AuthenticationConfig
import io.ktor.server.auth.OAuthAuthenticationProvider
import io.ktor.server.auth.oauth

private const val DEFAULT_SCHEME_NAME = "oauth2"

fun AuthenticationConfig.documentedOAuth2(
    name: String? = null,
    description: String? = null,
    authorizeUrl: Url? = null,
    accessTokenUrl: Url? = null,
    refreshTokenUrl: Url? = null,
    scopes: OAuth2ScopesBuilder = {},
    configure: OAuthAuthenticationProvider.Config.() -> Unit
) {
    val securityScheme = OAuth2SecurityScheme(
        description = description,
        flows = OAuthFlowsObject(
//            implicit = null, // TODO
//            password = null, // TODO
//            clientCredentials = null, // TODO
            authorizationCode = OAuthFlowObject(
                authorizationUrl = requireNotNull(authorizeUrl) { "Authorization URL must not be null" },
                tokenUrl = requireNotNull(accessTokenUrl) { "Token URL must not be null" },
                refreshUrl = refreshTokenUrl,
                scopes = OAuth2Scopes.Builder().apply(scopes).build()
            )
        )
    )
    oauth(name = name, configure = configure)
    OpenAPIGen.registerSecurityScheme(
        name = name ?: DEFAULT_SCHEME_NAME,
        securityScheme = securityScheme
    )
}
