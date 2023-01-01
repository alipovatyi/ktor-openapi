package dev.arli.openapi.mapper

import dev.arli.openapi.model.Server
import dev.arli.openapi.model.ServerObject

internal class ServerMapper(
    private val serverVariableMapper: ServerVariableMapper = ServerVariableMapper()
) {

    fun map(server: Server): ServerObject {
        return ServerObject(
            url = server.url,
            description = server.description,
            variables = server.variables.mapValues { (_, variable) -> serverVariableMapper.map(variable) }
        )
    }
}
