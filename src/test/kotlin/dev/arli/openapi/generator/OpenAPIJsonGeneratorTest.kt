package dev.arli.openapi.generator

import dev.arli.openapi.OpenAPIGenConfiguration
import io.ktor.http.Url
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class OpenAPIJsonGeneratorTest {

    private val contactJsonGenerator = ContactJsonGenerator()
    private val licenseJsonGenerator = LicenseJsonGenerator()
    private val infoJsonGenerator = InfoJsonGenerator(
        contactJsonGenerator = contactJsonGenerator,
        licenseJsonGenerator = licenseJsonGenerator
    )
    private val serverVariableJsonGenerator = ServerVariableJsonGenerator()
    private val serverJsonGenerator = ServerJsonGenerator(
        serverVariableJsonGenerator = serverVariableJsonGenerator
    )
    private val generator = OpenAPIJsonGenerator(
        infoJsonGenerator = infoJsonGenerator,
        serverJsonGenerator = serverJsonGenerator
    )

    @Test
    fun `Should generate OpenAPI json correctly`() {
        val givenConfiguration = OpenAPIGenConfiguration().apply {
            info {
                title = "Swagger Petstore - OpenAPI 3.0"
                description = "This is a sample Pet Store Server based on the OpenAPI 3.0 specification."
                version = "1.0.11"
                termsOfService = Url("https://swagger.io/terms/")

                contact {
                    email = "apiteam@swagger.io"
                }

                license {
                    name = "Apache 2.0"
                    url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
                }
            }

            server(Url("/v3")) {}
        }
        val expectedJsonObject = buildJsonObject {
            put("openapi", "3.0.3")
            putJsonObject("info") {
                put("title", "Swagger Petstore - OpenAPI 3.0")
                put("description", "This is a sample Pet Store Server based on the OpenAPI 3.0 specification.")
                put("version", "1.0.11")
                put("termsOfService", "https://swagger.io/terms/")
                putJsonObject("contact") {
                    put("email", "apiteam@swagger.io")
                }
                putJsonObject("license") {
                    put("name", "Apache 2.0")
                    put("url", "https://www.apache.org/licenses/LICENSE-2.0.html")
                }
            }
            putJsonArray("servers") {
                addJsonObject {
                    put("url", "http://localhost/v3")
                    putJsonObject("variables") {}
                }
            }
        }
        val actualJsonObject = generator.generate(givenConfiguration)

        assertEquals(expectedJsonObject, actualJsonObject)
    }
}
