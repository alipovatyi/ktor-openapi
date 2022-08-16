package dev.arli.openapi.swagger

data class SwaggerUIConfiguration(
    var path: String = "/",
    var webjarsPath: String = "webjars",
    internal val specificationFileName: String
) {
    val oauth2RedirectPath = "/$path/$OAUTH2_REDIRECT_PATH".replace("//+".toRegex(), "/")
}

private const val OAUTH2_REDIRECT_PATH = "oauth2-redirect"
