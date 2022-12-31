package dev.arli.openapi

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.Info
import dev.arli.openapi.model.Server
import dev.arli.openapi.model.ServerVariables
import dev.arli.openapi.model.Servers
import dev.arli.openapi.model.Tag
import dev.arli.openapi.model.Tags
import dev.arli.openapi.swagger.SwaggerUIConfiguration
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.DefaultJson
import org.junit.jupiter.api.Test

internal class OpenAPIGenConfigurationTest {

    @Test
    fun `Should return configuration with default values`() {
        val expectedConfiguration = OpenAPIGenConfiguration(
            json = DefaultJson,
            openAPIVersion = "3.0.3",
            info = null,
            servers = Servers(),
            outputDir = "openapi",
            outputFileName = "openapi.json",
            oauth2RedirectPath = "oauth2-redirect",
            tags = Tags(),
            swaggerUIConfiguration = SwaggerUIConfiguration(specificationFileName = "openapi.json")
        )
        val actualConfiguration = OpenAPIGenConfiguration()

        assertThat(actualConfiguration).isEqualTo(expectedConfiguration)
    }

    @Test
    fun `Should update output file configuration`() {
        val expectedConfiguration = OpenAPIGenConfiguration(
            outputDir = "docs",
            outputFileName = "openapi.json",
            swaggerUIConfiguration = SwaggerUIConfiguration(specificationFileName = "openapi.json")
        )
        val actualConfiguration = OpenAPIGenConfiguration().apply {
            outputDir = "docs"
        }

        assertThat(actualConfiguration).isEqualTo(expectedConfiguration)
    }

    @Test
    fun `Should update swagger ui configuration`() {
        val expectedConfiguration = OpenAPIGenConfiguration(
            swaggerUIConfiguration = SwaggerUIConfiguration(
                path = "/documentation",
                specificationFileName = "openapi.json"
            )
        )
        val actualConfiguration = OpenAPIGenConfiguration().apply {
            swaggerUI {
                path = "/documentation"
            }
        }

        assertThat(actualConfiguration).isEqualTo(expectedConfiguration)
    }

    @Test
    fun `Should update info object`() {
        val expectedConfiguration = OpenAPIGenConfiguration(
            info = Info(
                title = "Swagger Petstore - OpenAPI 3.0",
                description = "Description",
                termsOfService = Url("http://localhost/"),
                contact = null,
                license = null,
                version = "1.0.11"
            )
        )
        val actualConfiguration = OpenAPIGenConfiguration().apply {
            info(
                title = "Swagger Petstore - OpenAPI 3.0",
                version = "1.0.11"
            ) {
                description = "Description"
                termsOfService = Url("http://localhost/")
            }
        }

        assertThat(actualConfiguration).isEqualTo(expectedConfiguration)
    }

    @Test
    fun `Should add server object`() {
        val expectedConfiguration = OpenAPIGenConfiguration(
            servers = Servers(
                servers = listOf(
                    Server(
                        url = Url("http://localhost/server"),
                        description = "Server description",
                        variables = ServerVariables()
                    )
                )
            )
        )
        val actualConfiguration = OpenAPIGenConfiguration().apply {
            servers {
                server(Url("http://localhost/server")) {
                    description = "Server description"
                }
            }
        }

        assertThat(actualConfiguration).isEqualTo(expectedConfiguration)
    }

    @Test
    fun `Should add tag object`() {
        val expectedConfiguration = OpenAPIGenConfiguration(
            tags = Tags(listOf(Tag(name = "Tag", description = "Description", externalDocs = null)))
        )
        val actualConfiguration = OpenAPIGenConfiguration().apply {
            tags {
                tag(name = "Tag") {
                    description = "Description"
                }
            }
        }

        assertThat(actualConfiguration).isEqualTo(expectedConfiguration)
    }
}
