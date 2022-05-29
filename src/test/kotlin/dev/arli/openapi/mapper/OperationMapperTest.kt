package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Query
import dev.arli.openapi.model.ExternalDocumentationObject
import dev.arli.openapi.model.OperationObject
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.model.SchemaObject
import dev.arli.openapi.model.TagObject
import dev.arli.openapi.model.property.DataType
import dev.arli.openapi.model.property.IntegerFormat
import dev.arli.openapi.model.property.StringFormat
import io.ktor.http.Url
import io.ktor.server.routing.RootRouteSelector
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class OperationMapperTest {

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
            externalDocs = ExternalDocumentationObject(
                url = Url("http://localhost/external-docs")
            ),
            operationId = "operation-id",
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
                )
            ),
            requestBody = null,
            responses = emptyMap(),
            deprecated = false
        )

        assertEquals(expectedParameterObject, mapper.map(givenParams))
    }

    private fun createRoute(path: String): Route {
        val root = Route(null, RootRouteSelector())
        val normalizedPath = path + "/".takeIf { path.endsWith("/").not() }.orEmpty()
        return root.route(normalizedPath) {}
    }

    private data class TestRequest(
        @Path val pathParam1: String,
        @Path val pathParam2: Long,
        @Query val queryParam1: Int?,
        @Query val queryParam2: Boolean
    )

    private object TestResponse
}
