package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class InfoTest {

    @Test
    fun `Should create info`() {
        val expectedInfo = Info(
            title = "Swagger Petstore - OpenAPI 3.0",
            description = "This is a sample Pet Store Server based on the OpenAPI 3.0 specification.",
            termsOfService = Url("https://swagger.io/terms/"),
            contact = Contact(
                email = "apiteam@swagger.io",
                name = null,
                url = null
            ),
            license = License(
                name = "Apache 2.0",
                url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
            ),
            version = "1.0.11"
        )

        val actualInfo = Info.Builder(
            title = "Swagger Petstore - OpenAPI 3.0",
            version = "1.0.11"
        ).apply {
            description = "This is a sample Pet Store Server based on the OpenAPI 3.0 specification."

            termsOfService = Url("https://swagger.io/terms/")

            contact {
                email = "apiteam@swagger.io"
            }

            license(name = "Apache 2.0") {
                url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
            }
        }.build()

        assertThat(actualInfo).isEqualTo(expectedInfo)
    }

    @Test
    fun `Should create info with default values`() {
        val expectedInfo = Info(
            title = "Swagger Petstore - OpenAPI 3.0",
            description = null,
            termsOfService = null,
            contact = null,
            license = null,
            version = "1.0.11"
        )

        val actualInfo = Info.Builder(
            title = "Swagger Petstore - OpenAPI 3.0",
            version = "1.0.11"
        ).build()

        assertThat(actualInfo).isEqualTo(expectedInfo)
    }
}
