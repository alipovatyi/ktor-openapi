package dev.arli.openapi.generator

import dev.arli.openapi.model.InfoObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal class InfoJsonGenerator(
    private val contactJsonGenerator: ContactJsonGenerator = ContactJsonGenerator(),
    private val licenseJsonGenerator: LicenseJsonGenerator = LicenseJsonGenerator()
) {

    fun generateInfoJson(info: InfoObject): JsonObject {
        return buildJsonObject {
            put("title", info.title)
            info.description?.let { description ->
                put("description", description)
            }
            put("version", info.version)
            info.termsOfService?.let { termsOfService ->
                put("termsOfService", termsOfService.toString())
            }
            info.contact?.let { contact ->
                put("contact", contactJsonGenerator.generateContactJson(contact))
            }
            info.license?.let { license ->
                put("license", licenseJsonGenerator.generateLicenseJson(license))
            }
        }
    }
}
