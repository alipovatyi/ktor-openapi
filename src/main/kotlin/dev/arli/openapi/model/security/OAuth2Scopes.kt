package dev.arli.openapi.model.security

typealias OAuth2ScopesBuilder = OAuth2Scopes.Builder.() -> Unit

data class OAuth2Scopes(
    private val scopes: List<OAuth2Scope> = emptyList()
) : List<OAuth2Scope> by scopes {

    class Builder {
        val scopes: MutableList<OAuth2Scope> = mutableListOf()

        inline fun scope(name: String, builder: OAuth2ScopeBuilder = {}) {
            scopes += OAuth2Scope.Builder(name = name).apply(builder).build()
        }

        fun build(): OAuth2Scopes {
            return OAuth2Scopes(scopes = scopes)
        }
    }
}
