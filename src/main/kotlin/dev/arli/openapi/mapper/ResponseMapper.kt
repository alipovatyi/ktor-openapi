package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Header
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.model.HeaderComponent
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.model.MediaTypeObject
import dev.arli.openapi.model.ResponseComponent
import dev.arli.openapi.model.ResponseObject
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class ResponseMapper(
    private val headersMapper: HeadersMapper = HeadersMapper(),
    private val mediaTypeMapper: MediaTypeMapper = MediaTypeMapper()
) {

    fun map(responseClass: KClass<*>): ResponseComponent {
        val responseAnnotation = requireNotNull(responseClass.findAnnotation<Response>()) {
            "Response [${responseClass.simpleName}] must be annotated with @Response annotation"
        }
        val headers = mutableMapOf<String, HeaderComponent>()
        val content = mutableMapOf<MediaType, MediaTypeObject>()

        with(responseClass) {
            val annotatedHeaders = declaredMemberProperties.filter { it.hasAnnotation<Header>() }
            val annotatedContent = declaredMemberProperties.filter { it.hasAnnotation<Content>() }

            headers += headersMapper.map(annotatedHeaders)

            annotatedContent.forEach { contentProperty ->
                val contentAnnotation = requireNotNull(contentProperty.findAnnotation<Content>()) {
                    "Content [${contentProperty.name}] must be annotated with @Content annotation"
                }
                content[contentAnnotation.mediaType] = mediaTypeMapper.map(contentProperty)
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
