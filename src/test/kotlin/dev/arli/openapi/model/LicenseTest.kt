package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class LicenseTest {

    @Test
    fun `Should create license`() {
        val expectedLicense = License(
            name = "Apache 2.0",
            url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
        )

        val actualLicense = License.Builder(name = "Apache 2.0").apply {
            url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
        }.build()

        assertThat(actualLicense).isEqualTo(expectedLicense)
    }

    @Test
    fun `Should create license with default values`() {
        val expectedLicense = License(
            name = "Apache 2.0",
            url = null
        )

        val actualLicense = License.Builder(name = "Apache 2.0").build()

        assertThat(actualLicense).isEqualTo(expectedLicense)
    }
}
