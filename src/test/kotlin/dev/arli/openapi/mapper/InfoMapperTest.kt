package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.Contact
import dev.arli.openapi.model.ContactObject
import dev.arli.openapi.model.Info
import dev.arli.openapi.model.InfoObject
import dev.arli.openapi.model.License
import dev.arli.openapi.model.LicenseObject
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class InfoMapperTest {

    private val mapper = InfoMapper()

    @Test
    fun `Should map info to info object`() {
        val givenInfo = Info(
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
                url = null
            ),
            version = "1.0.11"
        )

        val expectedInfoObject = InfoObject(
            title = "Swagger Petstore - OpenAPI 3.0",
            description = "This is a sample Pet Store Server based on the OpenAPI 3.0 specification.",
            termsOfService = Url("https://swagger.io/terms/"),
            contact = ContactObject(email = "apiteam@swagger.io"),
            license = LicenseObject(name = "Apache 2.0"),
            version = "1.0.11"
        )

        assertThat(mapper.map(givenInfo)).isEqualTo(expectedInfoObject)
    }
}
