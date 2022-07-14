package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Cookie
import dev.arli.openapi.annotation.Header
import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Query
import dev.arli.openapi.model.ExternalDocumentationObject
import dev.arli.openapi.model.OperationObject
import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.model.TagObject
import dev.arli.openapi.parser.PathParametersParser
import dev.arli.openapi.model.Response
import io.ktor.server.routing.Route
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation

class OperationMapper(
    private val pathParametersParser: PathParametersParser = PathParametersParser(),
    private val pathParametersMapper: PathParametersMapper = PathParametersMapper(),
    private val queryParametersMapper: QueryParametersMapper = QueryParametersMapper(),
    private val headerParametersMapper: HeaderParametersMapper = HeaderParametersMapper(),
    private val cookieParametersMapper: CookieParametersMapper = CookieParametersMapper(),
    private val responseMapper: ResponseMapper = ResponseMapper()
) {

    fun map(params: Params): OperationObject {
        val path = params.route.parent.toString()
        val pathParameters = pathParametersParser.parse(path)

        val tags = params.tags.map { TagObject(name = it) }.toSet()
        val parameters = mutableListOf<ParameterComponent>()
        val responses = params.responses.associate { it.statusCode to responseMapper.map(it.responseClass) }

        with(params.requestClass) {
            val annotatedPathParameters = declaredMemberProperties.filter { it.hasAnnotation<Path>() }
            val annotatedQueryParameters = declaredMemberProperties.filter { it.hasAnnotation<Query>() }
            val annotatedHeaderParameters = declaredMemberProperties.filter { it.hasAnnotation<Header>() }
            val annotatedCookieParameters = declaredMemberProperties.filter { it.hasAnnotation<Cookie>() }

            parameters += pathParametersMapper.map(pathParameters, annotatedPathParameters)
            parameters += queryParametersMapper.map(annotatedQueryParameters)
            parameters += headerParametersMapper.map(annotatedHeaderParameters)
            parameters += cookieParametersMapper.map(annotatedCookieParameters)
        }

        return OperationObject(
            tags = tags,
            summary = params.summary,
            description = params.description,
            externalDocs = params.externalDocs,
            operationId = params.operationId,
            parameters = parameters,
            responses = responses,
            deprecated = params.deprecated
        )
    }

    data class Params(
        val route: Route,
        val requestClass: KClass<*>,
        val responseClass: KClass<*>,
        val tags: Set<String>,
        val summary: String?,
        val description: String?,
        val externalDocs: ExternalDocumentationObject?,
        val operationId: String?,
        val responses: List<Response<*>>,
        val deprecated: Boolean
    )
}
