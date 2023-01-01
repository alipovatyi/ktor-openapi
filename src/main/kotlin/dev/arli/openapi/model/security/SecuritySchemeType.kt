package dev.arli.openapi.model.security

internal enum class SecuritySchemeType(val key: String) {
    HTTP("http"),
    OAUTH2("oauth2")
}
