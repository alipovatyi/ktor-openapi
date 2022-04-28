package dev.arli.openapi.generator

import dev.arli.openapi.model.ServerObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject

class ServerJsonGenerator(
    private val serverVariableJsonGenerator: ServerVariableJsonGenerator
) {

    fun generateServerJson(server: ServerObject): JsonObject {
        return buildJsonObject {
            put("url", server.url.toString())
            server.description?.let { description ->
                put("description", description)
            }
            putJsonObject("variables") {
                server.variables.forEach { (name, variable) ->
                    put(name, serverVariableJsonGenerator.generateServerVariableJson(variable))
                }
            }
        }
    }
}
