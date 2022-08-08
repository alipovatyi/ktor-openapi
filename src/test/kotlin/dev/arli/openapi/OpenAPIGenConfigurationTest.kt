package dev.arli.openapi

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.InfoObject
import dev.arli.openapi.model.ServerObject
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
            servers = emptyList(),
            outputDir = "openapi",
            outputFileName = "openapi.json",
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
                webjarsPath = "/assets",
                specificationFileName = "openapi.json"
            )
        )
        val actualConfiguration = OpenAPIGenConfiguration().apply {
            swaggerUI {
                path = "/documentation"
                webjarsPath = "/assets"
            }
        }

        assertThat(actualConfiguration).isEqualTo(expectedConfiguration)
    }

    @Test
    fun `Should update info object`() {
        val expectedConfiguration = OpenAPIGenConfiguration(
            info = InfoObject(
                title = "Title",
                description = "Description",
                termsOfService = Url("http://localhost/")
            )
        )
        val actualConfiguration = OpenAPIGenConfiguration().apply {
            info {
                title = "Title"
                description = "Description"
                termsOfService = Url("http://localhost/")
            }
        }

        assertThat(actualConfiguration).isEqualTo(expectedConfiguration)
    }

    @Test
    fun `Should add server object`() {
        val expectedConfiguration = OpenAPIGenConfiguration(
            servers = listOf(
                ServerObject(
                    url = Url("http://localhost/server"),
                    description = "Server description"
                )
            )
        )
        val actualConfiguration = OpenAPIGenConfiguration().apply {
            server(Url("http://localhost/server")) {
                description = "Server description"
            }
        }

        assertThat(actualConfiguration).isEqualTo(expectedConfiguration)
    }
}
