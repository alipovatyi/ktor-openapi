package dev.arli.openapi.generator

import dev.arli.openapi.model.ContactObject
import io.ktor.http.Url
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ContactJsonGeneratorTest {

    private val generator = ContactJsonGenerator()

    @Test
    fun `Should convert contact to json object`() {
        val givenContact = ContactObject(
            name = "Contact",
            url = Url("http://localhost/contact"),
            email = "contact@mail.com"
        )
        val expectedJsonObject = buildJsonObject {
            put("name", "Contact")
            put("url", "http://localhost/contact")
            put("email", "contact@mail.com")
        }

        assertEquals(expectedJsonObject, generator.generateContactJson(givenContact))
    }

    @Test
    fun `Should exclude null values`() {
        val givenContact = ContactObject(
            name = null,
            url = null,
            email = null
        )
        val expectedJsonObject = buildJsonObject {}

        assertEquals(expectedJsonObject, generator.generateContactJson(givenContact))
    }
}
