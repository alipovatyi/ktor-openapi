package dev.arli.openapi.generator

import dev.arli.openapi.model.ExternalDocumentationObject
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.OperationObject
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.ResponseObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.TagObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.StringFormat
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import kotlin.test.assertEquals
import kotlinx.serialization.json.add
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class OperationJsonGeneratorTest {

    private val externalDocumentationJsonGenerator = ExternalDocumentationJsonGenerator()
    private val parameterJsonGenerator = ParameterJsonGenerator()
    private val requestBodyJsonGenerator = RequestBodyJsonGenerator()
    private val responseJsonGenerator = ResponseJsonGenerator()
    private val generator = OperationJsonGenerator(
        externalDocumentationJsonGenerator = externalDocumentationJsonGenerator,
        parameterJsonGenerator = parameterJsonGenerator,
        requestBodyJsonGenerator = requestBodyJsonGenerator,
        responseJsonGenerator = responseJsonGenerator
    )

    @Test
    fun `Should convert operation object to json object`() {
        val givenOperation = OperationObject(
            tags = setOf(TagObject("tag-1"), TagObject("tag-2"), TagObject("tag-3")),
            summary = "Operation summary",
            description = "Operation description",
            externalDocs = ExternalDocumentationObject(
                url = Url("http://localhost/external-docs")
            ),
            operationId = "operation-id",
            parameters = listOf(
                ParameterObject(
                    name = "pathParam",
                    `in` = ParameterLocation.PATH
                ),
                ParameterObject(
                    name = "queryParam",
                    `in` = ParameterLocation.QUERY
                )
            ),
            requestBody = null, // TODO
            responses = mapOf(
                null to ResponseObject(
                    description = "Default response",
                    content = mapOf(
                        MediaType.APPLICATION_JSON to MediaTypeObject(
                            schema = SchemaObject(
                                type = DataType.STRING,
                                format = StringFormat.DATE,
                                nullable = false
                            )
                        )
                    )
                ),
                HttpStatusCode.NotFound to ResponseObject(
                    description = "Not found",
                    content = mapOf(
                        MediaType.APPLICATION_JSON to MediaTypeObject(
                            schema = SchemaObject(
                                type = DataType.STRING,
                                format = StringFormat.NO_FORMAT,
                                nullable = false
                            )
                        )
                    )
                ),
            ),
            deprecated = true
        )
        val expectedJsonObject = buildJsonObject {
            putJsonArray("tags") {
                add("tag-1")
                add("tag-2")
                add("tag-3")
            }
            put("summary", "Operation summary")
            put("description", "Operation description")
            putJsonObject("externalDocs") {
                put("url", "http://localhost/external-docs")
            }
            put("operationId", "operation-id")
            putJsonArray("parameters") {
                addJsonObject {
                    put("name", "pathParam")
                    put("in", "path")
                    put("required", false)
                    put("deprecated", false)
                }
                addJsonObject {
                    put("name", "queryParam")
                    put("in", "query")
                    put("required", false)
                    put("deprecated", false)
                }
            }
//            putJsonObject("request") {} // TODO
            putJsonObject("responses") {
                putJsonObject("default") {
                    put("description", "Default response")
                    putJsonObject("content") {
                        putJsonObject("application/json") {
                            putJsonObject("schema") {
                                put("type", "string")
                                put("format", "date")
                                put("nullable", false)
                            }
                        }
                    }
                }
                putJsonObject("404") {
                    put("description", "Not found")
                    putJsonObject("content") {
                        putJsonObject("application/json") {
                            putJsonObject("schema") {
                                put("type", "string")
                                put("format", "")
                                put("nullable", false)
                            }
                        }
                    }
                }
            }
            put("deprecated", true)
        }

        assertEquals(expectedJsonObject, generator.generateOperationJson(givenOperation))
    }

    @Test
    fun `Should exclude null values`() {
        val givenOperation = OperationObject(
            summary = null,
            description = null,
            externalDocs = null,
            operationId = null,
            requestBody = null
        )
        val expectedJsonObject = buildJsonObject {
            putJsonArray("tags") {}
            putJsonArray("parameters") {}
            putJsonObject("responses") {}
            put("deprecated", false)
        }

        assertEquals(expectedJsonObject, generator.generateOperationJson(givenOperation))
    }
}
