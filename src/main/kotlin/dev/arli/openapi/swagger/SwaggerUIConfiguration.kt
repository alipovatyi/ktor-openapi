package dev.arli.openapi.swagger

data class SwaggerUIConfiguration(
    var path: String = "/",
    var webjarsPath: String = "webjars",
    internal val specificationFileName: String
)
