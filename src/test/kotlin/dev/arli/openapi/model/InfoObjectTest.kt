package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class InfoObjectTest {

    @Test
    fun `Should return info with default values`() {
        val expectedInfo = InfoObject(
            title = "Swagger Petstore - OpenAPI 3.0",
            description = null,
            termsOfService = null,
            contact = null,
            license = null,
            version = "1.0.11"
        )
        val actualInfo = InfoObject(
            title = "Swagger Petstore - OpenAPI 3.0",
            version = "1.0.11"
        )

        assertThat(actualInfo).isEqualTo(expectedInfo)
    }
}
