package dev.arli.openapi.util

fun String.removeTrailingSlash(): String {
    val path = toString()
    if (path != "/" && path.endsWith("/")) {
        return path.removeSuffix("/")
    }
    return path
}
