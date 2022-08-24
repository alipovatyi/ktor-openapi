package dev.arli.openapi.swagger

typealias SwaggerUIConfigurationBuilder = SwaggerUIConfiguration.Builder.() -> Unit

data class SwaggerUIConfiguration(
    val path: String = "/",
    val webjarsPath: String = "webjars",
    internal val specificationFileName: String
) {
    val oauth2RedirectPath = "/$path/$OAUTH2_REDIRECT_PATH".replace("//+".toRegex(), "/")

    class Builder(private val specificationFileName: String) {
        var path: String = "/"
        var webjarsPath: String = "webjars"

        fun build(): SwaggerUIConfiguration {
            return SwaggerUIConfiguration(
                path = path,
                webjarsPath = webjarsPath,
                specificationFileName = specificationFileName
            )
        }
    }
}

private const val OAUTH2_REDIRECT_PATH = "oauth2-redirect"
