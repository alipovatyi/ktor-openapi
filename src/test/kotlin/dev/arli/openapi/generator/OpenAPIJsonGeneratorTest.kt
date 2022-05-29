package dev.arli.openapi.generator

import dev.arli.openapi.OpenAPIGenConfiguration
import dev.arli.openapi.model.OperationObject
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.PathItemObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.TagObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import io.ktor.http.Url
import kotlin.test.assertEquals
import kotlinx.serialization.json.add
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

internal class OpenAPIJsonGeneratorTest {

    private val generator = OpenAPIJsonGenerator()

    @Test
    fun `Should generate OpenAPI json correctly`() {
        val givenConfiguration = OpenAPIGenConfiguration().apply {
            info {
                title = "Swagger Petstore - OpenAPI 3.0"
                description = "This is a sample Pet Store Server based on the OpenAPI 3.0 specification."
                version = "1.0.11"
                termsOfService = Url("https://swagger.io/terms/")

                contact {
                    email = "apiteam@swagger.io"
                }

                license {
                    name = "Apache 2.0"
                    url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
                }
            }

            server(Url("/v3")) {}
        }
        val givenPathItems = mapOf(
            "/pet/{petId}" to PathItemObject(
                summary = null,
                description = null,
                get = OperationObject(
                    tags = setOf(TagObject(name = "pet")),
                    summary = "Find pet by ID",
                    description = "Returns a single pet",
                    externalDocs = null,
                    operationId = null,
                    parameters = listOf(
                        ParameterObject(
                            name = "petId",
                            `in` = ParameterLocation.PATH,
                            description = "ID of pet to return",
                            required = true,
                            schema = SchemaObject(
                                type = DataType.INTEGER,
                                format = IntegerFormat.INT_64,
                                nullable = false
                            )
                        )
                    )
                )
            )
        )

        val expectedJsonObject = buildJsonObject {
            put("openapi", "3.0.3")
            putJsonObject("info") {
                put("title", "Swagger Petstore - OpenAPI 3.0")
                put("description", "This is a sample Pet Store Server based on the OpenAPI 3.0 specification.")
                put("version", "1.0.11")
                put("termsOfService", "https://swagger.io/terms/")
                putJsonObject("contact") {
                    put("email", "apiteam@swagger.io")
                }
                putJsonObject("license") {
                    put("name", "Apache 2.0")
                    put("url", "https://www.apache.org/licenses/LICENSE-2.0.html")
                }
            }
            putJsonArray("servers") {
                addJsonObject {
                    put("url", "http://localhost/v3")
                    putJsonObject("variables") {}
                }
            }
            putJsonObject("paths") {
                putJsonObject("/pet/{petId}") {
                    putJsonObject("get") {
                        putJsonArray("tags") {
                            add("pet")
                        }
                        put("summary", "Find pet by ID")
                        put("description", "Returns a single pet")
                        putJsonArray("parameters") {
                            addJsonObject {
                                put("name", "petId")
                                put("in", "path")
                                put("description", "ID of pet to return")
                                put("required", true)
                                put("deprecated", false)
                                putJsonObject("schema") {
                                    put("type", "integer")
                                    put("format", "int64")
                                    put("nullable", false)
                                }
                            }
                        }
                        putJsonObject("responses") {}
                        put("deprecated", false)
                    }
                }
            }
        }

        val actualJsonObject = generator.generate(
            configuration = givenConfiguration,
            pathItems = givenPathItems
        )

        assertEquals(expectedJsonObject, actualJsonObject)
    }
}
