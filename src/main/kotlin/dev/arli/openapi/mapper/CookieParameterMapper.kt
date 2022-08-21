package dev.arli.openapi.mapper

import dev.arli.openapi.annotation.Cookie
import dev.arli.openapi.model.ParameterComponent
import dev.arli.openapi.model.ParameterLocation
import dev.arli.openapi.model.ParameterObject
import dev.arli.openapi.parser.CookieParameterNameParser
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

internal class CookieParameterMapper(
    private val cookieParameterNameParser: CookieParameterNameParser = CookieParameterNameParser(),
    private val schemaComponentMapper: SchemaComponentMapper = SchemaComponentMapper()
) {

    fun map(property: KProperty<*>): ParameterComponent {
        val name = cookieParameterNameParser.parse(property)
        val cookieAnnotation = requireNotNull(property.findAnnotation<Cookie>()) {
            "Cookie parameter [$name] must be annotated with @Cookie annotation"
        }

        return ParameterObject(
            name = name,
            `in` = ParameterLocation.COOKIE,
            required = cookieAnnotation.required,
            description = cookieAnnotation.description,
            deprecated = cookieAnnotation.deprecated,
            schema = schemaComponentMapper.map(property)
        )
    }
}
