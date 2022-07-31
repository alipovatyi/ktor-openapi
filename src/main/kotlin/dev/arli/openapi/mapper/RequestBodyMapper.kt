package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.RequestBody
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.RequestBodyComponent
import dev.arli.openapi.model.RequestBodyExamples
import dev.arli.openapi.model.RequestBodyObject
import dev.arli.openapi.parser.DescriptionParser
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

class RequestBodyMapper(
    private val descriptionParser: DescriptionParser = DescriptionParser(),
    private val mediaTypeMapper: MediaTypeMapper = MediaTypeMapper()
) {

    fun map(kProperty: KProperty<*>, examples: RequestBodyExamples?): RequestBodyComponent {
        val requestBodyAnnotation = requireNotNull(kProperty.findAnnotation<RequestBody>()) {
            "Request body must be annotated with @RequestBody annotation"
        }
        val mediaType = requireNotNull(requestBodyAnnotation.mediaType.firstOrNull()) {
            "Request body must contain media type"
        }
        require(mediaType in supportedMediaTypes) {
            "Media type [$mediaType] is not supported yet. Supported media types: $supportedMediaTypes"
        }
        val mediaTypeParams = MediaTypeMapper.Params(
            kProperty = kProperty,
            mediaTypeExamples = examples?.get(mediaType)
        )
        val mediaTypes = mapOf(mediaType to mediaTypeMapper.map(mediaTypeParams)) // TODO: multiple types
        return RequestBodyObject(
            description = descriptionParser.parse(kProperty), // TODO: should be ignored in schema
            content = mediaTypes,
            required = requestBodyAnnotation.required
        )
    }

    private companion object {
        val supportedMediaTypes = listOf(
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_FORM
        )
    }
}
