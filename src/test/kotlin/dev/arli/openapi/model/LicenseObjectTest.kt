package dev.arli.openapi.model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class LicenseObjectTest {

    @Test
    fun `Should return license with default values`() {
        val expectedLicense = LicenseObject(
            name = null,
            url = null
        )
        val actualLicense = LicenseObject()

        assertEquals(expectedLicense, actualLicense)
    }
}
