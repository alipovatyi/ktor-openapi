package dev.arli.openapi.model

internal enum class ParameterLocation(val key: String) {
    PATH("path"),
    QUERY("query"),
    HEADER("header"),
    COOKIE("cookie")
}
