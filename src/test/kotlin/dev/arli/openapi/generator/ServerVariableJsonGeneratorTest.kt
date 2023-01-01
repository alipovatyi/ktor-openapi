package dev.arli.openapi.generator

import dev.arli.openapi.model.ServerVariableObject
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ServerVariableJsonGeneratorTest {

    private val generator = ServerVariableJsonGenerator()

    @Test
    fun `Should convert server variable to json object`() {
        val givenServerVariable = ServerVariableObject(
            enum = setOf("enum-1", "enum-2"),
            default = "default",
            description = "Variable description"
        )
        val expectedJsonObject = buildJsonObject {
            putJsonArray("enum") {
                add("enum-1")
                add("enum-2")
            }
            put("default", "default")
            put("description", "Variable description")
        }

        assertEquals(expectedJsonObject, generator.generateServerVariableJson(givenServerVariable))
    }

    @Test
    fun `Should exclude null values`() {
        val givenServerVariable = ServerVariableObject(
            enum = setOf("enum-1"),
            default = "default",
            description = null
        )
        val expectedJsonObject = buildJsonObject {
            putJsonArray("enum") {
                add("enum-1")
            }
            put("default", "default")
        }

        assertEquals(expectedJsonObject, generator.generateServerVariableJson(givenServerVariable))
    }
}
