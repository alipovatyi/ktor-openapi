package dev.arli.openapi.model

import dev.arli.openapi.model.security.HttpSecuritySchemeType
import dev.arli.openapi.model.security.SecuritySchemeType

sealed class SecuritySchemeObject(val type: SecuritySchemeType) : SecuritySchemeComponent {
    abstract val description: String?
}

data class HttpSecurityScheme(
    override val description: String?,
    val scheme: HttpSecuritySchemeType, // REQUIRED
    val bearerFormat: String? = null,
) : SecuritySchemeObject(SecuritySchemeType.HTTP)
