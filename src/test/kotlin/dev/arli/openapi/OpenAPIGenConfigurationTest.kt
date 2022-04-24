package dev.arli.openapi

import dev.arli.openapi.model.InfoObject
import io.ktor.http.Url
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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
}
