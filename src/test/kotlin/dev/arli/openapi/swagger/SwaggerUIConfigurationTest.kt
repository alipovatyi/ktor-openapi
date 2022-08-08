package dev.arli.openapi.swagger

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class SwaggerUIConfigurationTest {

    @Test
    fun `Should return swagger ui configuration with default values`() {
        val expectedSwaggerUIConfiguration = SwaggerUIConfiguration(
            path = "/",
            webjarsPath = "webjars",
            specificationFileName = "openapi.json"
        )

        val actualSwaggerUIConfiguration = SwaggerUIConfiguration(
            specificationFileName = "openapi.json"
        )

        assertThat(actualSwaggerUIConfiguration).isEqualTo(expectedSwaggerUIConfiguration)
    }
}
