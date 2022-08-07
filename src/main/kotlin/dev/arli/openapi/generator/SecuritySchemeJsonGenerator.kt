package dev.arli.openapi.generator

import dev.arli.openapi.generator.security.HttpSecuritySchemeJsonGenerator
import dev.arli.openapi.model.HttpSecurityScheme
import dev.arli.openapi.model.SecuritySchemeComponent
import dev.arli.openapi.model.SecuritySchemeObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

class SecuritySchemeJsonGenerator(
    private val httpSecuritySchemeJsonGenerator: HttpSecuritySchemeJsonGenerator = HttpSecuritySchemeJsonGenerator()
) {

    fun generateSecuritySchemeJson(securityScheme: SecuritySchemeComponent): JsonObject {
        return if (securityScheme !is SecuritySchemeObject) {
            buildJsonObject {} // TODO
        } else when (securityScheme) {
            is HttpSecurityScheme -> httpSecuritySchemeJsonGenerator.generateHttpSecuritySchemeJson(securityScheme)
        }
    }
}
