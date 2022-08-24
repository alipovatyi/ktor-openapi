package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Cookie
import dev.arli.openapi.annotation.Header
import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Query
import dev.arli.openapi.annotation.RequestBody
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.model.ExternalDocumentation
import dev.arli.openapi.model.ExternalDocumentationObject
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.OperationObject
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.RequestBodyExamples
import dev.arli.openapi.model.RequestBodyObject
import dev.arli.openapi.model.ResponseObject
import dev.arli.openapi.model.Responses
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.SecurityRequirementObject
import dev.arli.openapi.model.TagObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.StringFormat
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.server.auth.AuthenticationRouteSelector
import io.ktor.server.routing.RootRouteSelector
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import org.junit.jupiter.api.Test

internal class OperationMapperTest {

    private val json = Json
    private val mapper = OperationMapper()

    @Test
    fun `Should map params to operation object`() {
        val givenParams = OperationMapper.Params(
            route = createRoute("/test/{pathParam1}/test/{pathParam2}"),
            requestClass = TestRequest::class,
            responseClass = TestResponse::class,
            tags = setOf("tag-1", "tag-2"),
            summary = "Summary",
            description = "Description",
            externalDocs = ExternalDocumentation(
                url = Url("http://localhost/external-docs")
            ),
            operationId = "operation-id",
            requestBodyExamples = RequestBodyExamples.Builder(json = json).apply {
                applicationJson<String> {
                    example = "Example"
                }
            }.build(),
            responses = Responses.Builder(json = json).apply {
                defaultResponse<TestResponse, Int>()
                response<TestResponseNotFound, String>(HttpStatusCode.NotFound)
            }.build(),
            deprecated = false
        )

        val expectedParameterObject = OperationObject(
            tags = setOf(TagObject(name = "tag-1"), TagObject(name = "tag-2")),
            summary = "Summary",
            description = "Description",
            externalDocs = ExternalDocumentationObject(
                url = Url("http://localhost/external-docs")
            ),
            operationId = "operation-id",
            parameters = listOf(
                ParameterObject(
                    name = "pathParam1",
                    `in` = ParameterLocation.PATH,
                    description = "",
                    required = true,
                    schema = SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = false
                    )
                ),
                ParameterObject(
                    name = "pathParam2",
                    `in` = ParameterLocation.PATH,
                    description = "",
                    required = true,
                    schema = SchemaObject(
                        type = DataType.INTEGER,
                        format = IntegerFormat.INT_64,
                        nullable = false
                    )
                ),
                ParameterObject(
                    name = "queryParam1",
                    `in` = ParameterLocation.QUERY,
                    description = "",
                    schema = SchemaObject(
                        type = DataType.INTEGER,
                        format = IntegerFormat.INT_32,
                        nullable = true
                    )
                ),
                ParameterObject(
                    name = "queryParam2",
                    `in` = ParameterLocation.QUERY,
                    description = "",
                    schema = SchemaObject(
                        type = DataType.BOOLEAN,
                        format = null,
                        nullable = false
                    )
                ),
                ParameterObject(
                    name = "headerParam1",
                    `in` = ParameterLocation.HEADER,
                    description = "",
                    schema = SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = false
                    )
                ),
                ParameterObject(
                    name = "headerParam2",
                    `in` = ParameterLocation.HEADER,
                    description = "",
                    schema = SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = true
                    )
                ),
                ParameterObject(
                    name = "cookieParam1",
                    `in` = ParameterLocation.COOKIE,
                    description = "",
                    schema = SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = true
                    )
                ),
                ParameterObject(
                    name = "cookieParam2",
                    `in` = ParameterLocation.COOKIE,
                    description = "",
                    schema = SchemaObject(
                        type = DataType.STRING,
                        format = StringFormat.NO_FORMAT,
                        nullable = false
                    )
                )
            ),
            requestBody = RequestBodyObject(
                description = null,
                content = mapOf(
                    MediaType.APPLICATION_JSON to MediaTypeObject(
                        schema = SchemaObject(
                            type = DataType.STRING,
                            format = StringFormat.NO_FORMAT,
                            nullable = false
                        ),
                        example = "Example",
                        exampleJson = JsonPrimitive("Example"),
                        examples = emptyMap()
                    )
                )
            ),
            responses = mapOf(
                null to ResponseObject<Int>(
                    description = "Successful operation",
                    headers = emptyMap(),
                    content = mapOf(
                        MediaType.APPLICATION_JSON to MediaTypeObject(
                            schema = SchemaObject(
                                type = DataType.INTEGER,
                                format = IntegerFormat.INT_32,
                                nullable = false
                            )
                        )
                    ),
                    links = emptyMap()
                ),
                HttpStatusCode.NotFound to ResponseObject<String>(
                    description = "Element not found",
                    headers = emptyMap(),
                    content = mapOf(
                        MediaType.APPLICATION_JSON to MediaTypeObject(
                            schema = SchemaObject(
                                type = DataType.STRING,
                                format = StringFormat.NO_FORMAT,
                                nullable = false
                            )
                        )
                    ),
                    links = emptyMap()
                )
            ),
            deprecated = false,
            security = listOf(SecurityRequirementObject("basic"))
        )

        assertThat(mapper.map(givenParams)).isEqualTo(expectedParameterObject)
    }

    private fun createRoute(path: String): Route {
        val root = Route(
            selector = RootRouteSelector(),
            parent = Route(null, AuthenticationRouteSelector(listOf("basic")))
        )
        val normalizedPath = path + "/".takeIf { path.endsWith("/").not() }.orEmpty()
        return root.route(normalizedPath) {}
    }

    private data class TestRequest(
        @Path val pathParam1: String,
        @Path val pathParam2: Long,
        @Query val queryParam1: Int?,
        @Query val queryParam2: Boolean,
        @Header val headerParam1: String,
        @Header val headerParam2: String?,
        @Cookie val cookieParam1: String?,
        @Cookie val cookieParam2: String,
        @RequestBody val content: String
    )

    @Response(description = "Successful operation")
    private data class TestResponse(@Content val content: Int)

    @Response(description = "Element not found")
    private data class TestResponseNotFound(@Content val message: String)
}
