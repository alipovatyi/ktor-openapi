package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Cookie
import dev.arli.openapi.annotation.Header
import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Query
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.RequestBody
import dev.arli.openapi.model.ExternalDocumentation
import dev.arli.openapi.model.OperationObject
import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.model.RequestBodyExamples
import dev.arli.openapi.model.Response
import dev.arli.openapi.model.TagObject
import dev.arli.openapi.parser.PathParametersParser
import io.ktor.server.routing.Route
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

internal class OperationMapper(
    private val routePathMapper: RoutePathMapper = RoutePathMapper(),
    private val pathParametersParser: PathParametersParser = PathParametersParser(),
    private val pathParametersMapper: PathParametersMapper = PathParametersMapper(),
    private val queryParametersMapper: QueryParametersMapper = QueryParametersMapper(),
    private val headerParametersMapper: HeaderParametersMapper = HeaderParametersMapper(),
    private val cookieParametersMapper: CookieParametersMapper = CookieParametersMapper(),
    private val requestBodyMapper: RequestBodyMapper = RequestBodyMapper(),
    private val responseMapper: ResponseMapper = ResponseMapper(),
    private val securityRequirementsMapper: SecurityRequirementsMapper = SecurityRequirementsMapper(),
    private val externalDocumentationMapper: ExternalDocumentationMapper = ExternalDocumentationMapper()
) {

    fun map(params: Params): OperationObject {
        val path = routePathMapper.map(params.route)
        val pathParameters = pathParametersParser.parse(path)

        val request = requireNotNull(params.requestClass.findAnnotation<Request>()) {
            "Request [${params.requestClass.simpleName}] must be annotated with @Request annotation"
        }
        val tags = request.tags.map { TagObject(name = it) }.toSet()
        val parameters = mutableListOf<ParameterComponent>()
        val responses = params.responses.associate { it.statusCode to responseMapper.map(it) }
        val requestBodyProperty: KProperty<*>?

        with(params.requestClass) {
            val annotatedPathParameters = declaredMemberProperties.filter { it.hasAnnotation<Path>() }
            val annotatedQueryParameters = declaredMemberProperties.filter { it.hasAnnotation<Query>() }
            val annotatedHeaderParameters = declaredMemberProperties.filter { it.hasAnnotation<Header>() }
            val annotatedCookieParameters = declaredMemberProperties.filter { it.hasAnnotation<Cookie>() }

            parameters += pathParametersMapper.map(pathParameters, annotatedPathParameters)
            parameters += queryParametersMapper.map(annotatedQueryParameters)
            parameters += headerParametersMapper.map(annotatedHeaderParameters)
            parameters += cookieParametersMapper.map(annotatedCookieParameters)

            requestBodyProperty = declaredMemberProperties.firstOrNull { it.hasAnnotation<RequestBody>() }
        }

        return OperationObject(
            tags = tags,
            summary = request.summary,
            description = request.description,
            externalDocs = params.externalDocs?.let(externalDocumentationMapper::map),
            operationId = request.operationId,
            parameters = parameters,
            requestBody = requestBodyProperty?.let { requestBodyMapper.map(it, params.requestBodyExamples) },
            responses = responses,
            deprecated = request.deprecated,
            security = securityRequirementsMapper.map(params.route)
        )
    }

    data class Params(
        val route: Route,
        val requestClass: KClass<*>,
        val responseClass: KClass<*>,
        val externalDocs: ExternalDocumentation?,
        val requestBodyExamples: RequestBodyExamples?,
        val responses: List<Response<*, *>>
    )
}
