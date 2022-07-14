package dev.arli.openapi.generator

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.OpenAPIGenConfiguration
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.OperationObject
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.PathItemObject
import dev.arli.openapi.model.ResponseObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.TagObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.StringFormat
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
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
                    operationId = "getPetById",
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
                    ),
                    responses = mapOf(
                        HttpStatusCode.OK to ResponseObject(
                            description = "successful operation",
                            content = mapOf(
                                MediaType.APPLICATION_JSON to MediaTypeObject(
                                    schema = SchemaObject(
                                        type = DataType.OBJECT,
                                        format = null,
                                        nullable = false,
                                        properties = mapOf(
                                            "id" to SchemaObject(
                                                type = DataType.INTEGER,
                                                format = IntegerFormat.INT_64,
                                                nullable = false
                                            ),
                                            "name" to SchemaObject(
                                                type = DataType.STRING,
                                                format = StringFormat.NO_FORMAT,
                                                nullable = false
                                            ),
                                            "category" to SchemaObject(
                                                type = DataType.OBJECT,
                                                format = null,
                                                nullable = false,
                                                properties = mapOf(
                                                    "id" to SchemaObject(
                                                        type = DataType.INTEGER,
                                                        format = IntegerFormat.INT_64,
                                                        nullable = false
                                                    ),
                                                    "name" to SchemaObject(
                                                        type = DataType.STRING,
                                                        format = StringFormat.NO_FORMAT,
                                                        nullable = false
                                                    )
                                                )
                                            ),
                                            "photoUrls" to SchemaObject(
                                                type = DataType.ARRAY,
                                                format = null,
                                                nullable = false,
                                                items = SchemaObject(
                                                    type = DataType.STRING,
                                                    format = StringFormat.NO_FORMAT,
                                                    nullable = false
                                                )
                                            ),
                                            "tags" to SchemaObject(
                                                type = DataType.ARRAY,
                                                format = null,
                                                nullable = false,
                                                items = SchemaObject(
                                                    type = DataType.OBJECT,
                                                    format = null,
                                                    nullable = false,
                                                    properties = mapOf(
                                                        "id" to SchemaObject(
                                                            type = DataType.INTEGER,
                                                            format = IntegerFormat.INT_64,
                                                            nullable = false
                                                        ),
                                                        "name" to SchemaObject(
                                                            type = DataType.STRING,
                                                            format = StringFormat.NO_FORMAT,
                                                            nullable = false
                                                        )
                                                    )
                                                )
                                            ),
                                            "status" to SchemaObject(
                                                type = DataType.ENUM,
                                                format = null,
                                                nullable = false,
                                                description = "pet status in the store",
                                                enum = setOf("available","pending","sold")
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    ),
                    deprecated = false
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
                        put("operationId", "getPetById")
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
                        putJsonObject("responses") {
                            putJsonObject("200") {
                                put("description", "successful operation")
                                putJsonObject("content") {
                                    putJsonObject("application/json") {
                                        // TODO
//                                        putJsonArray("required") {
//                                            add("name")
//                                            add("photoUrls")
//                                        }
                                        putJsonObject("schema") {
                                            put("type", "object")
                                            put("nullable", false)
                                            putJsonObject("properties") {
                                                putJsonObject("id") {
                                                    put("type", "integer")
                                                    put("format", "int64")
                                                    put("nullable", false)
                                                }
                                                putJsonObject("name") {
                                                    put("type", "string")
                                                    put("format", "")
                                                    put("nullable", false)
                                                }
                                                putJsonObject("category") {
                                                    put("type", "object")
                                                    put("nullable", false)
                                                    putJsonObject("properties") {
                                                        putJsonObject("id") {
                                                            put("type", "integer")
                                                            put("format", "int64")
                                                            put("nullable", false)
                                                        }
                                                        putJsonObject("name") {
                                                            put("type", "string")
                                                            put("format", "")
                                                            put("nullable", false)
                                                        }
                                                    }
                                                }
                                                putJsonObject("photoUrls") {
                                                    put("type", "array")
                                                    put("nullable", false)
                                                    putJsonObject("items") {
                                                        put("type", "string")
                                                        put("format", "")
                                                        put("nullable", false)
                                                    }
                                                }
                                                putJsonObject("tags") {
                                                    put("type", "array")
                                                    put("nullable", false)
                                                    putJsonObject("items") {
                                                        put("type", "object")
                                                        put("nullable", false)
                                                        putJsonObject("properties") {
                                                            putJsonObject("id") {
                                                                put("type", "integer")
                                                                put("format", "int64")
                                                                put("nullable", false)
                                                            }
                                                            putJsonObject("name") {
                                                                put("type", "string")
                                                                put("format", "")
                                                                put("nullable", false)
                                                            }
                                                        }
                                                    }
                                                }
                                                putJsonObject("status") {
                                                    put("type", "string")
                                                    put("nullable", false)
                                                    put("description", "pet status in the store")
                                                    putJsonArray("enum") {
                                                        add("available")
                                                        add("pending")
                                                        add("sold")
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        put("deprecated", false)
                    }
                }
            }
        }

        val actualJsonObject = generator.generate(
            configuration = givenConfiguration,
            pathItems = givenPathItems
        )

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }
}
