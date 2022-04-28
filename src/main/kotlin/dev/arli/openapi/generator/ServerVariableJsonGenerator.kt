package dev.arli.openapi.generator

import dev.arli.openapi.model.ServerVariableObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

class ServerVariableJsonGenerator {

    fun generateServerVariableJson(serverVariable: ServerVariableObject): JsonObject {
        return buildJsonObject {
            putJsonArray("enum") {
                serverVariable.enum.forEach(::add)
            }
            put("default", serverVariable.default)
            serverVariable.description?.let { description ->
                put("description", description)
            }
        }
    }
}
