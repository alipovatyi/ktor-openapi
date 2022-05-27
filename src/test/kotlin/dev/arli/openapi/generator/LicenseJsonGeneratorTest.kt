package dev.arli.openapi.generator

import dev.arli.openapi.model.LicenseObject
import io.ktor.http.Url
import kotlin.test.assertEquals
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.jupiter.api.Test

internal class LicenseJsonGeneratorTest {

    private val generator = LicenseJsonGenerator()

    @Test
    fun `Should convert license to json object`() {
        val givenLicense = LicenseObject(
            name = "License",
            url = Url("http://localhost/license")
        )
        val expectedJsonObject = buildJsonObject {
            put("name", "License")
            put("url", "http://localhost/license")
        }

        assertEquals(expectedJsonObject, generator.generateLicenseJson(givenLicense))
    }

    @Test
    fun `Should exclude null values`() {
        val givenLicense = LicenseObject(
            name = "License",
            url = null
        )
        val expectedJsonObject = buildJsonObject {
            put("name", "License")
            put("url", "null")
        }

        assertEquals(expectedJsonObject, generator.generateLicenseJson(givenLicense))
    }
}
