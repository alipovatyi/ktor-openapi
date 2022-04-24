package dev.arli.openapi.generator

import dev.arli.openapi.model.LicenseObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class LicenseJsonGenerator {

    fun generateLicenseJson(license: LicenseObject): JsonObject {
        return buildJsonObject {
            put("name", license.name)
            put("url", license.url?.toString().toString())
        }
    }
}
