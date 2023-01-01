package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class LicenseObjectTest {

    @Test
    fun `Should return license with default values`() {
        val expectedLicense = LicenseObject(
            name = "Apache 2.0",
            url = null
        )
        val actualLicense = LicenseObject(name = "Apache 2.0")

        assertThat(actualLicense).isEqualTo(expectedLicense)
    }
}
