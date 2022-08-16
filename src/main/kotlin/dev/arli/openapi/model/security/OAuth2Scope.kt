package dev.arli.openapi.model.security

typealias OAuth2ScopeBuilder = OAuth2Scope.Builder.() -> Unit

data class OAuth2Scope(
    val name: String,
    val description: String?
) {

    class Builder(private val name: String) {
        var description: String? = null

        fun build(): OAuth2Scope {
            return OAuth2Scope(
                name = name,
                description = description
            )
        }
    }
}
