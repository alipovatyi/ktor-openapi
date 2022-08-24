package dev.arli.openapi.model.security

import io.ktor.http.Url

internal data class OAuthFlowObject(
    val authorizationUrl: Url?, // REQUIRED oauth2 ("implicit", "authorizationCode")
    val tokenUrl: Url?, // REQUIRED oauth2 ("password", "clientCredentials", "authorizationCode")
    val refreshUrl: Url?,
    val scopes: OAuth2Scopes // REQUIRED
)
