package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class ServerTest {

    @Test
    fun `Should create server`() {
        val expectedServer = Server(
            url = Url("http://localhost/server"),
            description = "Description",
            variables = ServerVariables(
                variables = mapOf(
                    "variable-1" to ServerVariable(
                        enum = setOf("value-11", "value-12"),
                        default = "value-11",
                        description = null
                    ),
                    "variable-2" to ServerVariable(
                        enum = setOf("value-21", "value-22"),
                        default = "value-21",
                        description = null
                    )
                )
            )
        )

        val actualServer = Server.Builder(url = Url("http://localhost/server")).apply {
            description = "Description"

            variables {
                variable(name = "variable-1", enum = setOf("value-11", "value-12"))
                variable(name = "variable-2", enum = setOf("value-21", "value-22"))
            }
        }.build()

        assertThat(actualServer).isEqualTo(expectedServer)
    }

    @Test
    fun `Should create server with default values`() {
        val expectedServer = Server(
            url = Url("http://localhost/server"),
            description = null,
            variables = ServerVariables()
        )

        val actualServer = Server.Builder(url = Url("http://localhost/server")).build()

        assertThat(actualServer).isEqualTo(expectedServer)
    }
}
