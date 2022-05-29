package dev.arli.openapi.generator

import dev.arli.openapi.OpenAPIGenConfiguration
import dev.arli.openapi.model.PathItemObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject

class OpenAPIJsonGenerator(
    private val infoJsonGenerator: InfoJsonGenerator = InfoJsonGenerator(),
    private val serverJsonGenerator: ServerJsonGenerator = ServerJsonGenerator(),
    private val pathItemJsonGenerator: PathItemJsonGenerator = PathItemJsonGenerator()
) {

    fun generate(
        configuration: OpenAPIGenConfiguration,
        pathItems: Map<String, PathItemObject>
    ): JsonObject {
        return buildJsonObject {
            put("openapi", configuration.openAPIVersion)
            put("info", infoJsonGenerator.generateInfoJson(requireNotNull(configuration.info)))
            putJsonArray("servers") {
                configuration.servers.forEach { server ->
                    add(serverJsonGenerator.generateServerJson(server))
                }
            }
            putJsonObject("paths") {
                pathItems.forEach { (path, pathItem) ->
                    put(path, pathItemJsonGenerator.generatePathItemJson(pathItem))
                }
            }
        }
    }
}
