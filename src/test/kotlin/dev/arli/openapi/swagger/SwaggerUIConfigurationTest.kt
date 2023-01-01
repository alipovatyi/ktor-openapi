package dev.arli.openapi.swagger

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class SwaggerUIConfigurationTest {

    @Test
    fun `Should create tag`() {
        val expectedSwaggerUIConfiguration = SwaggerUIConfiguration(
            path = "path",
            specificationFileName = "specification.json"
        )

        val actualSwaggerUIConfiguration = SwaggerUIConfiguration.Builder(
            specificationFileName = "specification.json"
        ).apply {
            path = "path"
        }.build()

        assertThat(actualSwaggerUIConfiguration).isEqualTo(expectedSwaggerUIConfiguration)
    }

    @Test
    fun `Should return swagger ui configuration with default values`() {
        val expectedSwaggerUIConfiguration = SwaggerUIConfiguration(
            path = "/",
            specificationFileName = "openapi.json"
        )

        val actualSwaggerUIConfiguration = SwaggerUIConfiguration(
            specificationFileName = "openapi.json"
        )

        assertThat(actualSwaggerUIConfiguration).isEqualTo(expectedSwaggerUIConfiguration)
    }
}
