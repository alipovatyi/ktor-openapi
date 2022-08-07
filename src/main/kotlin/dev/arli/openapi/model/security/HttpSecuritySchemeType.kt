package dev.arli.openapi.model.security

enum class HttpSecuritySchemeType(val key: String) {
    BASIC("basic"),
    BEARER("bearer")
}
