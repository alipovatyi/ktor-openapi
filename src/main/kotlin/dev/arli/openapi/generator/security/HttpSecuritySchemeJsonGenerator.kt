package dev.arli.openapi.generator.security

import dev.arli.openapi.model.HttpSecurityScheme
import dev.arli.openapi.model.security.HttpSecuritySchemeType
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class HttpSecuritySchemeJsonGenerator {

    fun generateHttpSecuritySchemeJson(httpSecurityScheme: HttpSecurityScheme): JsonObject {
        return buildJsonObject {
            put("type", httpSecurityScheme.type.key)
            httpSecurityScheme.description?.let { description ->
                put("description", description)
            }
            put("scheme", httpSecurityScheme.scheme.key)
            if (httpSecurityScheme.scheme == HttpSecuritySchemeType.BEARER) {
                httpSecurityScheme.bearerFormat?.let { bearerFormat ->
                    put("bearerFormat", bearerFormat)
                }
            }
        }
    }
}
