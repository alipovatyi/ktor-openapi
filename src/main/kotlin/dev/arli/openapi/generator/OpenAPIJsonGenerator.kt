package dev.arli.openapi.generator

import dev.arli.openapi.OpenAPIGenConfiguration
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class OpenAPIJsonGenerator(
    private val infoJsonGenerator: InfoJsonGenerator
) {

    fun generate(configuration: OpenAPIGenConfiguration): JsonObject {
        return buildJsonObject {
            put("openapi", configuration.openAPIVersion)
            put("info", infoJsonGenerator.generateInfoJson(requireNotNull(configuration.info)))
        }
    }
}
