package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class ServerVariableTest {

    @Test
    fun `Should create server variable`() {
        val expectedServerVariable = ServerVariable(
            enum = setOf("value-1", "value-2", "value-3"),
            default = "value-2",
            description = "Description"
        )

        val actualServerVariable = ServerVariable.Builder(
            enum = setOf("value-1", "value-2", "value-3")
        ).apply {
            default = "value-2"
            description = "Description"
        }.build()

        assertThat(actualServerVariable).isEqualTo(expectedServerVariable)
    }

    @Test
    fun `Should create server with default values`() {
        val expectedServerVariable = ServerVariable(
            enum = setOf("value-1", "value-2", "value-3"),
            default = "value-1",
            description = null
        )

        val actualServerVariable = ServerVariable.Builder(
            enum = setOf("value-1", "value-2", "value-3")
        ).build()

        assertThat(actualServerVariable).isEqualTo(expectedServerVariable)
    }

    @Test
    fun `Should throw exception if enum is empty`() {
        assertFailsWith<IllegalArgumentException> {
            ServerVariable.Builder(enum = emptySet()).build()
        }
    }
}
