package dev.arli.openapi.generator.security

import dev.arli.openapi.model.security.OAuthFlowsObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

internal class OAuthFlowsJsonGenerator(
    private val oAuthAuthorizationCodeFlowJsonGenerator: OAuthAuthorizationCodeFlowJsonGenerator = OAuthAuthorizationCodeFlowJsonGenerator(),
    private val oAuthImplicitFlowJsonGenerator: OAuthImplicitFlowJsonGenerator = OAuthImplicitFlowJsonGenerator()
) {

    fun generateOAuthFlowsJson(oAuthFlowsObject: OAuthFlowsObject): JsonObject {
        return buildJsonObject {
            // TODO: clientCredentials, password
            oAuthFlowsObject.implicit?.let { implicit ->
                put(
                    key = "implicit",
                    element = oAuthImplicitFlowJsonGenerator.generateOAuthImplicitFlowJson(oAuthFlowObject = implicit)
                )
            }
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
