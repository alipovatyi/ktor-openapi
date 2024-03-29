package dev.arli.openapi.generator

import dev.arli.openapi.model.LicenseObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal class LicenseJsonGenerator {

    fun generateLicenseJson(license: LicenseObject): JsonObject {
        return buildJsonObject {
            put("name", license.name)
            license.url?.let { url -> put("url", url.toString()) }
        }
    }
}
