package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Header
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.model.HeaderComponent
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.ResponseComponent
import dev.arli.openapi.model.ResponseObject
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import dev.arli.openapi.model.Response as ResponseModel

class ResponseMapper(
    private val headersMapper: HeadersMapper = HeadersMapper(),
    private val mediaTypeMapper: MediaTypeMapper = MediaTypeMapper()
) {

    fun <RESPONSE : Any, CONTENT> map(response: ResponseModel<RESPONSE, CONTENT>): ResponseComponent {
        val responseAnnotation = requireNotNull(response.responseClass.findAnnotation<Response>()) {
            "Response [${response.responseClass.simpleName}] must be annotated with @Response annotation"
        }
        val headers = mutableMapOf<String, HeaderComponent>()
        val content = mutableMapOf<MediaType, MediaTypeObject<CONTENT>>()

        with(response.responseClass) {
            val annotatedHeaders = declaredMemberProperties.filter { it.hasAnnotation<Header>() }
            val annotatedContent = declaredMemberProperties.filter { it.hasAnnotation<Content>() }

            headers += headersMapper.map(annotatedHeaders)

            annotatedContent.forEach { contentProperty ->
                // TODO: handle sealed classes
                val contentAnnotation = requireNotNull(contentProperty.findAnnotation<Content>()) {
                    "Content [${contentProperty.name}] must be annotated with @Content annotation"
                }
                val mediaTypeParams = MediaTypeMapper.Params(
                    kProperty = contentProperty,
                    example = response.example,
                    exampleJson = response.exampleJson,
                    examples = response.examples
                )
                content[contentAnnotation.mediaType] = mediaTypeMapper.map(mediaTypeParams)
            }
        }

        return ResponseObject(
            description = responseAnnotation.description,
            headers = headers,
            content = content,
            links = emptyMap() // TODO: map links
        )
    }
}
