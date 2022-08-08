package dev.arli.openapi.generator

import dev.arli.openapi.model.ContactObject
import dev.arli.openapi.model.InfoObject
import dev.arli.openapi.model.LicenseObject
import io.ktor.http.Url
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class InfoJsonGeneratorTest {

    private val contactJsonGenerator = ContactJsonGenerator()
    private val licenseJsonGenerator = LicenseJsonGenerator()
    private val generator = InfoJsonGenerator(
        contactJsonGenerator = contactJsonGenerator,
        licenseJsonGenerator = licenseJsonGenerator
    )

    @Test
    fun `Should convert info to json object`() {
        val givenInfo = InfoObject(
            title = "Awesome system",
            description = "OpenAPI documentation",
            version = "1.2.3",
            termsOfService = Url("http://localhost/terms"),
            contact = ContactObject(
                name = "Contact",
                url = Url("http://localhost/contact"),
                email = "contact@mail.com"
            ),
            license = LicenseObject(
                name = "License",
                url = Url("http://localhost/license")
            )
        )
        val expectedJsonObject = buildJsonObject {
            put("title", "Awesome system")
            put("description", "OpenAPI documentation")
            put("version", "1.2.3")
            put("termsOfService", "http://localhost/terms")
            putJsonObject("contact") {
                put("name", "Contact")
                put("url", "http://localhost/contact")
                put("email", "contact@mail.com")
            }
            putJsonObject("license") {
                put("name", "License")
                put("url", "http://localhost/license")
            }
        }

        assertEquals(expectedJsonObject, generator.generateInfoJson(givenInfo))
    }

    @Test
    fun `Should exclude termsOfService if it is null`() {
        val givenInfo = InfoObject(
            title = "Awesome system",
            description = "OpenAPI documentation",
            version = "1.2.3",
            termsOfService = null,
            contact = ContactObject(
                name = "Contact",
                url = Url("http://localhost/contact"),
                email = "contact@mail.com"
            ),
            license = LicenseObject(
                name = "License",
                url = Url("http://localhost/license")
            )
        )
        val expectedJsonObject = buildJsonObject {
            put("title", "Awesome system")
            put("description", "OpenAPI documentation")
            put("version", "1.2.3")
            putJsonObject("contact") {
                put("name", "Contact")
                put("url", "http://localhost/contact")
                put("email", "contact@mail.com")
            }
            putJsonObject("license") {
                put("name", "License")
                put("url", "http://localhost/license")
            }
        }

        assertEquals(expectedJsonObject, generator.generateInfoJson(givenInfo))
    }

    @Test
    fun `Should exclude contact if it is null`() {
        val givenInfo = InfoObject(
            title = "Awesome system",
            description = "OpenAPI documentation",
            version = "1.2.3",
            termsOfService = Url("http://localhost/terms"),
            contact = null,
            license = LicenseObject(
                name = "License",
                url = Url("http://localhost/license")
            )
        )
        val expectedJsonObject = buildJsonObject {
            put("title", "Awesome system")
            put("description", "OpenAPI documentation")
            put("version", "1.2.3")
            put("termsOfService", "http://localhost/terms")
            putJsonObject("license") {
                put("name", "License")
                put("url", "http://localhost/license")
            }
        }

        assertEquals(expectedJsonObject, generator.generateInfoJson(givenInfo))
    }

    @Test
    fun `Should exclude license if it is null`() {
        val givenInfo = InfoObject(
            title = "Awesome system",
            description = "OpenAPI documentation",
            version = "1.2.3",
            termsOfService = Url("http://localhost/terms"),
            contact = ContactObject(
                name = "Contact",
                url = Url("http://localhost/contact"),
                email = "contact@mail.com"
            ),
            license = null
        )
        val expectedJsonObject = buildJsonObject {
            put("title", "Awesome system")
            put("description", "OpenAPI documentation")
            put("version", "1.2.3")
            put("termsOfService", "http://localhost/terms")
            putJsonObject("contact") {
                put("name", "Contact")
                put("url", "http://localhost/contact")
                put("email", "contact@mail.com")
            }
        }

        assertEquals(expectedJsonObject, generator.generateInfoJson(givenInfo))
    }
}
