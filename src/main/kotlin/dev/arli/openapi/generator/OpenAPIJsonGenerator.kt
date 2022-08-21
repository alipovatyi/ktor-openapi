package dev.arli.openapi.generator

import dev.arli.openapi.OpenAPIGenConfiguration
import dev.arli.openapi.model.PathItemObject
import dev.arli.openapi.model.SecuritySchemeComponent
import dev.arli.openapi.model.TagObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject

class OpenAPIJsonGenerator(
    private val infoJsonGenerator: InfoJsonGenerator = InfoJsonGenerator(),
    private val serverJsonGenerator: ServerJsonGenerator = ServerJsonGenerator(),
    private val pathItemJsonGenerator: PathItemJsonGenerator = PathItemJsonGenerator(),
    private val securitySchemeJsonGenerator: SecuritySchemeJsonGenerator = SecuritySchemeJsonGenerator(),
    private val tagJsonGenerator: TagJsonGenerator = TagJsonGenerator()
) {

    fun generate(
        configuration: OpenAPIGenConfiguration,
        pathItems: Map<String, PathItemObject>,
        securitySchemes: Map<String, SecuritySchemeComponent>,
        tags: List<TagObject>
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
            putJsonObject("components") {
                if (securitySchemes.isNotEmpty()) {
                    putJsonObject("securitySchemes") {
                        securitySchemes.forEach { (name, securityScheme) ->
                            put(name, securitySchemeJsonGenerator.generateSecuritySchemeJson(securityScheme))
                        }
                    }
                }
            }
            putJsonArray("tags") {
                tags.forEach { tag -> add(tagJsonGenerator.generateTagJson(tag)) }
            }
        }
    }
}
