package dev.arli.openapi.generator

import dev.arli.openapi.model.RequestBodyComponent
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

class RequestBodyJsonGenerator {

    fun generateRequestBodyJson(requestBody: RequestBodyComponent): JsonObject {
        return buildJsonObject {}
    }
}
