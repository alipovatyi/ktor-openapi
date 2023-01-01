package dev.arli.openapi.model.security

internal enum class HttpSecuritySchemeType(val key: String) {
    BASIC("basic"),
    BEARER("bearer")
}
