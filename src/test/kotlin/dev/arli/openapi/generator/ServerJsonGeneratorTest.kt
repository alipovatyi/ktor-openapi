package dev.arli.openapi.generator

import dev.arli.openapi.model.ServerObject
import dev.arli.openapi.model.ServerVariableObject
import io.ktor.http.Url
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ServerJsonGeneratorTest {

    private val serverVariableJsonGenerator = ServerVariableJsonGenerator()
    private val generator = ServerJsonGenerator(
        serverVariableJsonGenerator = serverVariableJsonGenerator
    )

    @Test
    fun `Should convert server variable to json object`() {
        val givenServer = ServerObject(
            url = Url("http://localhost/server"),
            description = "Server description",
            variables = mapOf(
                "variable-1" to ServerVariableObject(
                    enum = setOf("enum-1"),
                    default = "default"
                ),
                "variable-2" to ServerVariableObject(
                    enum = setOf("enum-2"),
                    default = "default"
                )
            )
        )
        val expectedJsonObject = buildJsonObject {
            put("url", "http://localhost/server")
            put("description", "Server description")
            putJsonObject("variables") {
                putJsonObject("variable-1") {
                    putJsonArray("enum") {
                        add("enum-1")
                    }
                    put("default", "default")
                }
                putJsonObject("variable-2") {
                    putJsonArray("enum") {
                        add("enum-2")
                    }
                    put("default", "default")
                }
            }
        }

        assertEquals(expectedJsonObject, generator.generateServerJson(givenServer))
    }

    @Test
    fun `Should exclude null values`() {
        val givenServer = ServerObject(
            url = Url("http://localhost/server")
        )
        val expectedJsonObject = buildJsonObject {
            put("url", "http://localhost/server")
            putJsonObject("variables") {}
        }

        assertEquals(expectedJsonObject, generator.generateServerJson(givenServer))
    }
}
