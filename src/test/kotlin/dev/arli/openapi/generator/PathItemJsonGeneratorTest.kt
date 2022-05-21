package dev.arli.openapi.generator

import dev.arli.openapi.model.OperationObject
import dev.arli.openapi.model.PathItemObject
import kotlin.test.assertEquals
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class PathItemJsonGeneratorTest {

    private val externalDocumentationJsonGenerator = ExternalDocumentationJsonGenerator()
    private val parameterJsonGenerator = ParameterJsonGenerator()
    private val requestBodyJsonGenerator = RequestBodyJsonGenerator()
    private val responseJsonGenerator = ResponseJsonGenerator()
    private val operationJsonGenerator = OperationJsonGenerator(
        externalDocumentationJsonGenerator = externalDocumentationJsonGenerator,
        parameterJsonGenerator = parameterJsonGenerator,
        requestBodyJsonGenerator = requestBodyJsonGenerator,
        responseJsonGenerator = responseJsonGenerator
    )
    private val generator = PathItemJsonGenerator(
        operationJsonGenerator = operationJsonGenerator
    )

    @Test
    fun `Should convert path item to json object`() {
        val givenPathItem = PathItemObject(
            summary = "Path summary",
            description = "Path description",
            get = OperationObject(
                description = "GET operation"
            ),
            put = OperationObject(
                description = "PUT operation"
            ),
            post = OperationObject(
                description = "POST operation"
            ),
            delete = OperationObject(
                description = "DELETE operation"
            )
        )
        val expectedJsonObject = buildJsonObject {
            put("summary", "Path summary")
            put("description", "Path description")
            putJsonObject("get") {
                putJsonArray("tags") {}
                put("description", "GET operation")
                putJsonArray("parameters") {}
                putJsonObject("responses") {}
                put("deprecated", false)
            }
            putJsonObject("put") {
                putJsonArray("tags") {}
                put("description", "PUT operation")
                putJsonArray("parameters") {}
                putJsonObject("responses") {}
                put("deprecated", false)
            }
            putJsonObject("post") {
                putJsonArray("tags") {}
                put("description", "POST operation")
                putJsonArray("parameters") {}
                putJsonObject("responses") {}
                put("deprecated", false)
            }
            putJsonObject("delete") {
                putJsonArray("tags") {}
                put("description", "DELETE operation")
                putJsonArray("parameters") {}
                putJsonObject("responses") {}
                put("deprecated", false)
            }
        }

        assertEquals(expectedJsonObject, generator.generatePathItemJson(givenPathItem))
    }

    @Test
    fun `Should exclude null values`() {
        val givenPathItem = PathItemObject()
        val expectedJsonObject = buildJsonObject {}

        assertEquals(expectedJsonObject, generator.generatePathItemJson(givenPathItem))
    }
}
