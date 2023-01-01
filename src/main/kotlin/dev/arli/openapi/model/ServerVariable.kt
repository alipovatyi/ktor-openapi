package dev.arli.openapi.model

typealias ServerVariableBuilder = ServerVariable.Builder.() -> Unit

data class ServerVariable(
    val enum: Set<String>,
    val default: String,
    val description: String?
) {

    class Builder(
        private val enum: Set<String> // SHOULD NOT BE EMPTY
    ) {
        var default: String? = null
        var description: String? = null

        fun build(): ServerVariable {
            require(enum.isNotEmpty()) {
                "Server variable's enum should not be empty"
            }
            return ServerVariable(
                enum = enum,
                default = default ?: enum.first(),
                description = description
            )
        }
    }
}
