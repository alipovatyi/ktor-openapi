package dev.arli.openapi.model

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

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
