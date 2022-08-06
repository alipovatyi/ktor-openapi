package dev.arli.openapi.util

import io.ktor.server.auth.AuthenticationRouteSelector
import io.ktor.server.routing.HttpMethodRouteSelector
import io.ktor.server.routing.Route
import io.ktor.server.routing.TrailingSlashRouteSelector

fun Route?.getPath(): String {
    if (this == null) return  "/"
    return when (val parentRoute = parent?.getPath()) {
        null -> when (selector) {
            is AuthenticationRouteSelector,
            is HttpMethodRouteSelector,
            is TrailingSlashRouteSelector -> "/"
            else -> "/$selector"
        }
        else -> when (selector) {
            is AuthenticationRouteSelector,
            is HttpMethodRouteSelector,
            is TrailingSlashRouteSelector -> if (parentRoute.endsWith('/')) parentRoute else "$parentRoute/"
            else -> if (parentRoute.endsWith('/')) "$parentRoute$selector" else "$parentRoute/$selector"
        }
    }.removeTrailingSlash()
}
