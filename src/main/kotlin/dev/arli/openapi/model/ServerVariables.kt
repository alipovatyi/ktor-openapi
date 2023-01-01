package dev.arli.openapi.model

typealias ServerVariablesBuilder = ServerVariables.Builder.() -> Unit

data class ServerVariables(
    private val variables: Map<String, ServerVariable> = emptyMap()
) : Map<String, ServerVariable> by variables {

    class Builder {
        val variables: MutableMap<String, ServerVariable> = mutableMapOf()

        inline fun variable(name: String, enum: Set<String>, builder: ServerVariableBuilder = {}) {
            variables[name] = ServerVariable.Builder(enum = enum).apply(builder).build()
        }

        fun build(): ServerVariables {
            return ServerVariables(variables = variables.toMap())
        }
    }
}
