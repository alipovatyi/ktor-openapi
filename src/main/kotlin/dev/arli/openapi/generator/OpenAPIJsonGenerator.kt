package dev.arli.openapi.generator

import dev.arli.openapi.OpenAPIGenConfiguration
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

class OpenAPIJsonGenerator(
    private val infoJsonGenerator: InfoJsonGenerator,
    private val serverJsonGenerator: ServerJsonGenerator
) {

    fun generate(configuration: OpenAPIGenConfiguration): JsonObject {
        return buildJsonObject {
            put("openapi", configuration.openAPIVersion)
            put("info", infoJsonGenerator.generateInfoJson(requireNotNull(configuration.info)))
            putJsonArray("servers") {
                configuration.servers.forEach { server ->
                    add(serverJsonGenerator.generateServerJson(server))
                }
            }
        }
    }
}
