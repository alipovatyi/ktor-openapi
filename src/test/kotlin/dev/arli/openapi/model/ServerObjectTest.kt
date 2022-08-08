package dev.arli.openapi.model

import io.ktor.http.Url
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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

        assertEquals(expectedServer, actualServer)
    }

    @Test
    fun `Should add server variable object`() {
        val expectedServer = ServerObject(
            url = Url("http://localhost/server"),
            description = null,
            variables = mapOf(
                "variable-1" to ServerVariableObject(description = "Variable 1"),
                "variable-2" to ServerVariableObject(description = "Variable 2")
            )
        )
        val actualServer = ServerObject(Url("http://localhost/server")).apply {
            variable("variable-1") { description = "Variable 1" }
            variable("variable-2") { description = "Variable 2" }
        }

        assertEquals(expectedServer, actualServer)
    }
}
