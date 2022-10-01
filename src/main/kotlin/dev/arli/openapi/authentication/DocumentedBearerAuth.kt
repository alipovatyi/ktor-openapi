package dev.arli.openapi.authentication

import dev.arli.openapi.OpenAPIGen
import dev.arli.openapi.model.HttpSecurityScheme
import dev.arli.openapi.model.security.HttpSecuritySchemeType
import io.ktor.server.auth.AuthenticationConfig
import io.ktor.server.auth.AuthenticationProvider

fun <PROVIDER : AuthenticationProvider> AuthenticationConfig.documentedBearer(
    name: String,
    description: String? = null,
    configure: () -> PROVIDER
) {
    val securityScheme = HttpSecurityScheme(
        description = description,
        scheme = HttpSecuritySchemeType.BEARER,
        bearerFormat = null
    )
    register(configure())
    OpenAPIGen.registerSecurityScheme(
        name = name,
        securityScheme = securityScheme
    )
}
