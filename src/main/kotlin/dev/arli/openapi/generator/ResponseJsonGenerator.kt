package dev.arli.openapi.generator

import dev.arli.openapi.model.ResponseComponent
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

class ResponseJsonGenerator {

    fun generateResponseJson(response: ResponseComponent): JsonObject {
        return buildJsonObject {}
    }
}
