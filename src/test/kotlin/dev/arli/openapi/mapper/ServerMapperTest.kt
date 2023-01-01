package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.Server
import dev.arli.openapi.model.ServerObject
import dev.arli.openapi.model.ServerVariable
import dev.arli.openapi.model.ServerVariableObject
import dev.arli.openapi.model.ServerVariables
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class ServerMapperTest {

    private val mapper = ServerMapper()

    @Test
    fun `Should map server to server object`() {
        val givenServer = Server(
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

        val expectedServerObject = ServerObject(
            url = Url("http://localhost/server"),
            description = "Description",
            variables = mapOf(
                "variable-1" to ServerVariableObject(
                    enum = setOf("value-11", "value-12"),
                    default = "value-11",
                    description = null
                ),
                "variable-2" to ServerVariableObject(
                    enum = setOf("value-21", "value-22"),
                    default = "value-21",
                    description = null
                )
            )
        )

        assertThat(mapper.map(givenServer)).isEqualTo(expectedServerObject)
    }
}
