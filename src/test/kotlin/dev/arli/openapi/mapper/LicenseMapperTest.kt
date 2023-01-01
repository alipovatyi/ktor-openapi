package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.License
import dev.arli.openapi.model.LicenseObject
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class LicenseMapperTest {

    private val mapper = LicenseMapper()

    @Test
    fun `Should map license to license object`() {
        val givenLicense = License(
            name = "Apache 2.0",
            url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
        )

        val expectedLicenseObject = LicenseObject(
            name = "Apache 2.0",
            url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
        )

        assertThat(mapper.map(givenLicense)).isEqualTo(expectedLicenseObject)
    }
}
