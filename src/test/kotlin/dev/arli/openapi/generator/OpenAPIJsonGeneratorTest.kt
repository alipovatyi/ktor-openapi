package dev.arli.openapi.generator

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.OpenAPIGenConfiguration
import dev.arli.openapi.model.ComponentsObject
import dev.arli.openapi.model.ContactObject
import dev.arli.openapi.model.HttpSecurityScheme
import dev.arli.openapi.model.InfoObject
import dev.arli.openapi.model.LicenseObject
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.OpenAPIObject
import dev.arli.openapi.model.OperationObject
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.PathItemObject
import dev.arli.openapi.model.RequestBodyObject
import dev.arli.openapi.model.ResponseObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.ServerObject
import dev.arli.openapi.model.TagObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.StringFormat
import dev.arli.openapi.model.security.HttpSecuritySchemeType
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
            info(
                title = "Swagger Petstore - OpenAPI 3.0",
                version = "1.0.11"
            ) {
                description = "This is a sample Pet Store Server based on the OpenAPI 3.0 specification."

                termsOfService = Url("https://swagger.io/terms/")

                contact {
                    email = "apiteam@swagger.io"
                }

                license(name = "Apache 2.0") {
                    url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
                }
            }

            servers {
                server(Url("/v3"))
            }
        }
        val givenPathItems = mapOf(
            "/pet" to PathItemObject(
                summary = null,
                description = null,
                post = OperationObject(
                    tags = setOf(TagObject(name = "pet")),
                    summary = "Add a new pet to the store",
                    description = "Add a new pet to the store",
                    externalDocs = null,
                    operationId = "addPet",
                    requestBody = RequestBodyObject(
                        description = null,
                        content = mapOf(
                            MediaType.APPLICATION_JSON to MediaTypeObject<Any>(
                                schema = SchemaObject(
                                    type = DataType.OBJECT,
                                    format = null,
                                    nullable = false,
                                    properties = mapOf(
                                        "name" to SchemaObject(
                                            type = DataType.STRING,
                                            format = StringFormat.NO_FORMAT,
                                            nullable = false
                                        ),
                                        "categoryId" to SchemaObject(
                                            type = DataType.INTEGER,
                                            format = IntegerFormat.INT_64,
                                            nullable = false
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
                                        "tagIds" to SchemaObject(
                                            type = DataType.ARRAY,
                                            format = null,
                                            nullable = false,
                                            items = SchemaObject(
                                                type = DataType.INTEGER,
                                                format = IntegerFormat.INT_64,
                                                nullable = false
                                            )
                                        ),
                                        "status" to SchemaObject(
                                            type = DataType.ENUM,
                                            format = null,
                                            nullable = false,
                                            description = "pet status in the store",
                                            enum = setOf("available", "pending", "sold")
                                        )
                                    )
                                ),
                                example = null,
                                exampleJson = null,
                                examples = emptyMap()
                            )
                        ),
                        required = true
                    ),
                    responses = mapOf(
                        HttpStatusCode.Created to ResponseObject<Any>(
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
                                                enum = setOf("available", "pending", "sold")
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            ),
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
                        HttpStatusCode.OK to ResponseObject<Any>(
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
                                                enum = setOf("available", "pending", "sold")
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
        val givenSecuritySchemes = mapOf(
            "basic" to HttpSecurityScheme(
                description = "Basic auth security scheme",
                scheme = HttpSecuritySchemeType.BASIC
            )
        )
        val givenTags = listOf(
            TagObject(name = "pet", description = "Everything about your Pets"),
            TagObject(name = "store", description = "Access to Petstore orders"),
            TagObject(name = "user", description = "Operations about user")
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
                putJsonObject("/pet") {
                    putJsonObject("post") {
                        putJsonArray("tags") {
                            add("pet")
                        }
                        put("summary", "Add a new pet to the store")
                        put("description", "Add a new pet to the store")
                        put("operationId", "addPet")
                        putJsonArray("parameters") {}
                        putJsonObject("requestBody") {
                            putJsonObject("content") {
                                putJsonObject("application/json") {
                                    putJsonObject("schema") {
                                        put("type", "object")
                                        put("nullable", false)
                                        putJsonObject("properties") {
                                            putJsonObject("name") {
                                                put("type", "string")
                                                put("format", "")
                                                put("nullable", false)
                                            }
                                            putJsonObject("categoryId") {
                                                put("type", "integer")
                                                put("format", "int64")
                                                put("nullable", false)
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
                                            putJsonObject("tagIds") {
                                                put("type", "array")
                                                put("nullable", false)
                                                putJsonObject("items") {
                                                    put("type", "integer")
                                                    put("format", "int64")
                                                    put("nullable", false)
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
                            put("required", true)
                        }
                        putJsonObject("responses") {
                            putJsonObject("201") {
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
            putJsonObject("components") {
                putJsonObject("securitySchemes") {
                    putJsonObject("basic") {
                        put("type", "http")
                        put("description", "Basic auth security scheme")
                        put("scheme", "basic")
                    }
                }
            }
            putJsonArray("tags") {
                addJsonObject {
                    put("name", "pet")
                    put("description", "Everything about your Pets")
                }
                addJsonObject {
                    put("name", "store")
                    put("description", "Access to Petstore orders")
                }
                addJsonObject {
                    put("name", "user")
                    put("description", "Operations about user")
                }
            }
        }

        val actualJsonObject = generator.generate(
            openAPIObject = OpenAPIObject(
                openapi = "3.0.3",
                info = InfoObject(
                    title = "Swagger Petstore - OpenAPI 3.0",
                    description = "This is a sample Pet Store Server based on the OpenAPI 3.0 specification.",
                    termsOfService = Url("https://swagger.io/terms/"),
                    contact = ContactObject(email = "apiteam@swagger.io"),
                    license = LicenseObject(
                        name = "Apache 2.0",
                        url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
                    ),
                    version = "1.0.11"
                ),
                servers = listOf(ServerObject(url = Url("http://localhost/v3"))),
                paths = givenPathItems,
                components = ComponentsObject(securitySchemes = givenSecuritySchemes),
                tags = givenTags
            )
        )

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }

    @Test
    fun `Should exclude null values`() {
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
                }
            }
            putJsonArray("servers") {}
            putJsonObject("paths") {}
            putJsonObject("components") {}
            putJsonArray("tags") {}
        }

        val actualJsonObject = generator.generate(
            openAPIObject = OpenAPIObject(
                openapi = "3.0.3",
                info = InfoObject(
                    title = "Swagger Petstore - OpenAPI 3.0",
                    description = "This is a sample Pet Store Server based on the OpenAPI 3.0 specification.",
                    termsOfService = Url("https://swagger.io/terms/"),
                    contact = ContactObject(email = "apiteam@swagger.io"),
                    license = LicenseObject(name = "Apache 2.0"),
                    version = "1.0.11"
                ),
                servers = emptyList(),
                paths = emptyMap(),
                components = ComponentsObject(
                    securitySchemes = emptyMap()
                ),
                tags = emptyList()
            )
        )

        assertThat(actualJsonObject).isEqualTo(expectedJsonObject)
    }
}
