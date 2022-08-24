package dev.arli.openapi.mapper

import dev.arli.openapi.model.ServerVariable
import dev.arli.openapi.model.ServerVariableObject

internal class ServerVariableMapper {

    fun map(serverVariable: ServerVariable): ServerVariableObject {
        require(serverVariable.enum.isNotEmpty()) {
            "Server variable's enum should not be empty"
        }
        return ServerVariableObject(
            enum = serverVariable.enum,
            default = serverVariable.default,
            description = serverVariable.description
        )
    }
}
