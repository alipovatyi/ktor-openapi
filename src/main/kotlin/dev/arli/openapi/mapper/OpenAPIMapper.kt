package dev.arli.openapi.mapper

import dev.arli.openapi.OpenAPIGenConfiguration
import dev.arli.openapi.model.ComponentsObject
import dev.arli.openapi.model.OpenAPIObject
import dev.arli.openapi.model.PathItemObject
import dev.arli.openapi.model.SecuritySchemeComponent
import dev.arli.openapi.model.Tags

internal class OpenAPIMapper(
    private val infoMapper: InfoMapper = InfoMapper(),
    private val serverMapper: ServerMapper = ServerMapper(),
    private val tagMapper: TagMapper = TagMapper()
) {

    fun map(
        openAPIGenConfiguration: OpenAPIGenConfiguration,
        pathItems: Map<String, PathItemObject>,
        securitySchemes: Map<String, SecuritySchemeComponent>,
        tags: Tags
    ): OpenAPIObject {
        val info = requireNotNull(openAPIGenConfiguration.info) {
            "Info must not be null"
        }
        return OpenAPIObject(
            openapi = openAPIGenConfiguration.openAPIVersion,
            info = infoMapper.map(info),
            servers = openAPIGenConfiguration.servers.map(serverMapper::map),
            paths = pathItems,
            components = ComponentsObject(
                securitySchemes = securitySchemes
            ),
            tags = tags.map(tagMapper::map)
        )
    }
}
