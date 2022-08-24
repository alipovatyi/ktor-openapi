package dev.arli.openapi.generator

import dev.arli.openapi.model.OpenAPIObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject

internal class OpenAPIJsonGenerator(
    private val infoJsonGenerator: InfoJsonGenerator = InfoJsonGenerator(),
    private val serverJsonGenerator: ServerJsonGenerator = ServerJsonGenerator(),
    private val pathItemJsonGenerator: PathItemJsonGenerator = PathItemJsonGenerator(),
    private val securitySchemeJsonGenerator: SecuritySchemeJsonGenerator = SecuritySchemeJsonGenerator(),
    private val tagJsonGenerator: TagJsonGenerator = TagJsonGenerator()
) {

    fun generate(openAPIObject: OpenAPIObject): JsonObject {
        return buildJsonObject {
            put("openapi", openAPIObject.openapi)
            put("info", infoJsonGenerator.generateInfoJson(openAPIObject.info))
            putJsonArray("servers") {
                openAPIObject.servers.forEach { server ->
                    add(serverJsonGenerator.generateServerJson(server))
                }
            }
            putJsonObject("paths") {
                openAPIObject.paths.forEach { (path, pathItem) ->
                    put(path, pathItemJsonGenerator.generatePathItemJson(pathItem))
                }
            }
            putJsonObject("components") {
                with(openAPIObject.components) {
                    if (securitySchemes.isNotEmpty()) {
                        putJsonObject("securitySchemes") {
                            securitySchemes.forEach { (name, securityScheme) ->
                                put(name, securitySchemeJsonGenerator.generateSecuritySchemeJson(securityScheme))
                            }
                        }
                    }
                }
            }
            putJsonArray("tags") {
                openAPIObject.tags.forEach { tag -> add(tagJsonGenerator.generateTagJson(tag)) }
            }
        }
    }
}
