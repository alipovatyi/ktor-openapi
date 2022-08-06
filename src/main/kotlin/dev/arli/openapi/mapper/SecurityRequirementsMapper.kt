package dev.arli.openapi.mapper

import dev.arli.openapi.model.SecurityRequirementObject
import io.ktor.server.auth.AuthenticationRouteSelector
import io.ktor.server.routing.Route

class SecurityRequirementsMapper {

    fun map(route: Route): List<SecurityRequirementObject> {
        return getAuthProviders(route)
            .filterNotNull()
            .map(::SecurityRequirementObject)
    }

    private tailrec fun getAuthProviders(
        parent: Route?,
        authProviders: List<String?> = emptyList()
    ): List<String?> {
        if (parent == null) return authProviders
        val authSelector = parent.selector as? AuthenticationRouteSelector
        return getAuthProviders(parent.parent, authProviders + authSelector?.names.orEmpty())
    }
}
