package dev.arli.openapi.generator

import dev.arli.openapi.generator.security.HttpSecuritySchemeJsonGenerator
import dev.arli.openapi.generator.security.OAuth2SecuritySchemeJsonGenerator
import dev.arli.openapi.model.HttpSecurityScheme
import dev.arli.openapi.model.OAuth2SecurityScheme
import dev.arli.openapi.model.SecuritySchemeComponent
import dev.arli.openapi.model.SecuritySchemeObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

internal class SecuritySchemeJsonGenerator(
    private val httpSecuritySchemeJsonGenerator: HttpSecuritySchemeJsonGenerator = HttpSecuritySchemeJsonGenerator(),
    private val oAuth2SecuritySchemeJsonGenerator: OAuth2SecuritySchemeJsonGenerator = OAuth2SecuritySchemeJsonGenerator()
) {

    fun generateSecuritySchemeJson(securityScheme: SecuritySchemeComponent): JsonObject {
        return if (securityScheme !is SecuritySchemeObject) {
            buildJsonObject {} // TODO
        } else {
            when (securityScheme) {
                is HttpSecurityScheme -> {
                    httpSecuritySchemeJsonGenerator.generateHttpSecuritySchemeJson(securityScheme)
                }
                is OAuth2SecurityScheme -> {
                    oAuth2SecuritySchemeJsonGenerator.generateOAuth2SecuritySchemeJson(securityScheme)
                }
            }
        }
    }
}
