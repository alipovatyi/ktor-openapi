package dev.arli.openapi.generator.security

import dev.arli.openapi.model.OAuth2SecurityScheme
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class OAuth2SecuritySchemeJsonGenerator(
    private val oAuthFlowsJsonGenerator: OAuthFlowsJsonGenerator = OAuthFlowsJsonGenerator()
) {

    fun generateOAuth2SecuritySchemeJson(oAuth2SecurityScheme: OAuth2SecurityScheme): JsonObject {
        return buildJsonObject {
            put("type", oAuth2SecurityScheme.type.key)
            oAuth2SecurityScheme.description?.let { description ->
                put("description", description)
            }
            put("flows", oAuthFlowsJsonGenerator.generateOAuthFlowsJson(oAuth2SecurityScheme.flows))
        }
    }
}
