package dev.arli.openapi.mapper

import dev.arli.openapi.util.removeTrailingSlash
import io.ktor.server.auth.AuthenticationRouteSelector
import io.ktor.server.routing.HttpMethodRouteSelector
import io.ktor.server.routing.Route
import io.ktor.server.routing.TrailingSlashRouteSelector

internal class RoutePathMapper {

    fun map(route: Route?): String {
        if (route == null) return "/"
        val selector = route.selector
        return when (val parentRoute = route.parent?.let(::map)) {
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
}
