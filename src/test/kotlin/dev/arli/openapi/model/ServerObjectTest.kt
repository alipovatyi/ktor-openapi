package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class ServerObjectTest {

    @Test
    fun `Should return server with default values`() {
        val expectedServer = ServerObject(
            url = Url("http://localhost/server"),
            description = null,
            variables = emptyMap()
        )
        val actualServer = ServerObject(
            url = Url("http://localhost/server")
        )

        assertThat(actualServer).isEqualTo(expectedServer)
    }
}
