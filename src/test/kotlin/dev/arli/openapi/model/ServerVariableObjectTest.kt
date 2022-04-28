package dev.arli.openapi.model

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class ServerVariableObjectTest {

    @Test
    fun `Should return server variable with default values`() {
        val expectedServerVariable = ServerVariableObject(
            enum = emptySet(),
            default = null,
            description = null
        )
        val actualServerVariable = ServerVariableObject()

        assertEquals(expectedServerVariable, actualServerVariable)
    }
}
