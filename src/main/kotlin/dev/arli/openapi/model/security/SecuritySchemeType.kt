package dev.arli.openapi.model.security

enum class SecuritySchemeType(val key: String) {
    HTTP("http"),
    OAUTH2("oauth2"),
}
