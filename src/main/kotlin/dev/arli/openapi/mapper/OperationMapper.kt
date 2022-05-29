package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Query
import dev.arli.openapi.model.ExternalDocumentationObject
import dev.arli.openapi.model.OperationObject
import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.model.TagObject
import dev.arli.openapi.parser.PathParametersParser
import io.ktor.server.routing.Route
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation

class OperationMapper(
    private val pathParametersParser: PathParametersParser = PathParametersParser(),
    private val pathParametersMapper: PathParametersMapper = PathParametersMapper(),
    private val queryParametersMapper: QueryParametersMapper = QueryParametersMapper()
) {

    fun map(params: Params): OperationObject {
        val path = params.route.parent.toString()
        val pathParameters = pathParametersParser.parse(path)

        val annotatedPathParameters = params.requestClass.declaredMemberProperties.filter { it.hasAnnotation<Path>() }
        val annotatedQueryParameters = params.requestClass.declaredMemberProperties.filter { it.hasAnnotation<Query>() }

        val tags = params.tags.map { TagObject(name = it) }.toSet()
        val parameters = mutableListOf<ParameterComponent>()

        parameters += pathParametersMapper.map(pathParameters, annotatedPathParameters)
        parameters += queryParametersMapper.map(annotatedQueryParameters)

        return OperationObject(
            tags = tags,
            summary = params.summary,
            description = params.description,
            externalDocs = params.externalDocs,
            operationId = params.operationId,
            parameters = parameters,
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
        val deprecated: Boolean
    )
}
