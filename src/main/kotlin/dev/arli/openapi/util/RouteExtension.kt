package dev.arli.openapi.util

import io.ktor.server.routing.Route

fun Route?.getPath(): String {
    return this?.parent?.toString()?.removeTrailingSlash() ?: "/"
}
