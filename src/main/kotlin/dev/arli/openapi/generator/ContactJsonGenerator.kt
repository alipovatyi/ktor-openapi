package dev.arli.openapi.generator

import dev.arli.openapi.model.ContactObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal class ContactJsonGenerator {

    fun generateContactJson(contact: ContactObject): JsonObject {
        return buildJsonObject {
            contact.name?.let { name ->
                put("name", name)
            }
            contact.url?.let { url ->
                put("url", url.toString())
            }
            contact.email?.let { email ->
                put("email", email)
            }
        }
    }
}
