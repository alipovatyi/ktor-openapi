package dev.arli.openapi.generator.security

import dev.arli.openapi.model.security.OAuthFlowObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject

internal class OAuthImplicitFlowJsonGenerator {

    fun generateOAuthImplicitFlowJson(oAuthFlowObject: OAuthFlowObject): JsonObject {
        val authorizationUrl = requireNotNull(oAuthFlowObject.authorizationUrl) {
            "Authorization URL must not be null"
        }
        return buildJsonObject {
            put("authorizationUrl", authorizationUrl.toString())
            putJsonObject("scopes") {
                oAuthFlowObject.scopes.forEach { (scope, description) ->
                    put(scope, description.orEmpty())
                }
            }
        }
    }
}
