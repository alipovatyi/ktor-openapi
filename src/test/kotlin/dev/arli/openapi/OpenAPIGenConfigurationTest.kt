package dev.arli.openapi

import dev.arli.openapi.model.InfoObject
import dev.arli.openapi.model.ServerObject
import io.ktor.http.Url
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class OpenAPIGenConfigurationTest {

    @Test
    fun `Should return configuration with default values`() {
        val expectedConfiguration = OpenAPIGenConfiguration(
            openAPIVersion = "3.0.3",
            info = null
        )
        val actualConfiguration = OpenAPIGenConfiguration()

        assertEquals(expectedConfiguration, actualConfiguration)
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

        assertEquals(expectedConfiguration, actualConfiguration)
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

        assertEquals(expectedConfiguration, actualConfiguration)
    }
}
