package dev.arli.openapi.generator.security

import dev.arli.openapi.model.security.OAuthFlowsObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

class OAuthFlowsJsonGenerator(
    private val oAuthAuthorizationCodeFlowJsonGenerator: OAuthAuthorizationCodeFlowJsonGenerator = OAuthAuthorizationCodeFlowJsonGenerator()
) {

    fun generateOAuthFlowsJson(oAuthFlowsObject: OAuthFlowsObject): JsonObject {
        return buildJsonObject {
            // TODO: implicit, clientCredentials, password
            oAuthFlowsObject.authorizationCode?.let { authorizationCode ->
                put(
                    key = "authorizationCode",
                    element = oAuthAuthorizationCodeFlowJsonGenerator.generateOAuthAuthorizationCodeFlowJson(
                        oAuthFlowObject = authorizationCode
                    )
                )
            }
        }
    }
}
