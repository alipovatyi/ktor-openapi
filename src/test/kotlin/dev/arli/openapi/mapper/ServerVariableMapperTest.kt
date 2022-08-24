package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.ServerVariable
import dev.arli.openapi.model.ServerVariableObject
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class ServerVariableMapperTest {

    private val mapper = ServerVariableMapper()

    @Test
    fun `Should map server variable to server variable object`() {
        val givenServerVariable = ServerVariable(
            enum = setOf("value-1", "value-2", "value-3"),
            default = "value-2",
            description = "Description"
        )

        val expectedServerVariableObject = ServerVariableObject(
            enum = setOf("value-1", "value-2", "value-3"),
            default = "value-2",
            description = "Description"
        )

        assertThat(mapper.map(givenServerVariable)).isEqualTo(expectedServerVariableObject)
    }

    @Test
    fun `Should throw exceptions if enum is empty`() {
        val givenServerVariable = ServerVariable(
            enum = emptySet(),
            default = "value-1",
            description = "Description"
        )

        assertFailsWith<IllegalArgumentException> {
            mapper.map(givenServerVariable)
        }
    }
}
