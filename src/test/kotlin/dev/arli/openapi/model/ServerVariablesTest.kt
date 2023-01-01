package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class ServerVariablesTest {

    @Test
    fun `Should create server variables`() {
        val expectedServerVariables = ServerVariables(
            variables = mapOf(
                "variable-1" to ServerVariable(
                    enum = setOf("value-1"),
                    default = "value-1",
                    description = "Description"
                ),
                "variable-2" to ServerVariable(
                    enum = setOf("value-2"),
                    default = "value-2",
                    description = null
                )
            )
        )

        val actualServerVariables = ServerVariables.Builder().apply {
            variable(name = "variable-1", enum = setOf("value-1")) {
                description = "Description"
            }
            variable(name = "variable-2", enum = setOf("value-2"))
        }.build()

        assertThat(actualServerVariables).isEqualTo(expectedServerVariables)
    }
}
