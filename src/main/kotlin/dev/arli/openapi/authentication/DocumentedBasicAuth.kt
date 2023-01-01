package dev.arli.openapi.authentication

import dev.arli.openapi.OpenAPIGen
import dev.arli.openapi.model.HttpSecurityScheme
import dev.arli.openapi.model.security.HttpSecuritySchemeType
import io.ktor.server.auth.AuthenticationConfig
import io.ktor.server.auth.BasicAuthenticationProvider
import io.ktor.server.auth.basic

private const val DEFAULT_SCHEME_NAME = "basic"

fun AuthenticationConfig.documentedBasic(
    name: String? = null,
    description: String? = null,
    configure: BasicAuthenticationProvider.Config.() -> Unit
) {
    val securityScheme = HttpSecurityScheme(
        description = description,
        scheme = HttpSecuritySchemeType.BASIC
    )
    basic(name = name, configure = configure)
    OpenAPIGen.registerSecurityScheme(
        name = name ?: DEFAULT_SCHEME_NAME,
        securityScheme = securityScheme
    )
}
