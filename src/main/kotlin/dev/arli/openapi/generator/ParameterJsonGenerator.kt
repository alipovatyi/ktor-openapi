package dev.arli.openapi.generator

import dev.arli.openapi.model.ParameterComponent
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

// TODO
class ParameterJsonGenerator {

    fun generateParameterJson(parameter: ParameterComponent): JsonObject {
        return buildJsonObject {}
    }
}
