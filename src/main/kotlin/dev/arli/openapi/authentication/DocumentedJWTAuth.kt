package dev.arli.openapi.authentication

import dev.arli.openapi.OpenAPIGen
import dev.arli.openapi.model.HttpSecurityScheme
import dev.arli.openapi.model.security.HttpSecuritySchemeType
import io.ktor.server.auth.AuthenticationConfig
import io.ktor.server.auth.jwt.JWTAuthenticationProvider
import io.ktor.server.auth.jwt.jwt

private const val DEFAULT_SCHEME_NAME = "bearer"

fun AuthenticationConfig.documentedJWT(
    name: String? = null,
    description: String? = null,
    configure: JWTAuthenticationProvider.Config.() -> Unit
) {
    val securityScheme = HttpSecurityScheme(
        description = description,
        scheme = HttpSecuritySchemeType.BEARER,
        bearerFormat = null
    )
    jwt(name = name, configure = configure)
    OpenAPIGen.registerSecurityScheme(
        name = name ?: DEFAULT_SCHEME_NAME,
        securityScheme = securityScheme
    )
}
