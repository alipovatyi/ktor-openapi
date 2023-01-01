package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class ServersTest {

    @Test
    fun `Should create servers`() {
        val expectedServers = Servers(
            servers = listOf(
                Server(
                    url = Url("http://localhost/server-1"),
                    description = "Description",
                    variables = ServerVariables()
                ),
                Server(
                    url = Url("http://localhost/server-2"),
                    description = null,
                    variables = ServerVariables()
                )
            )
        )

        val actualServers = Servers.Builder().apply {
            server(url = Url("http://localhost/server-1")) {
                description = "Description"
            }
            server(url = Url("http://localhost/server-2"))
        }.build()

        assertThat(actualServers).isEqualTo(expectedServers)
    }
}
