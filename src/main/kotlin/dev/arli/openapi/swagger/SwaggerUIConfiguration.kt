package dev.arli.openapi.swagger

typealias SwaggerUIConfigurationBuilder = SwaggerUIConfiguration.Builder.() -> Unit

data class SwaggerUIConfiguration internal constructor(
    val path: String = "/",
    internal val specificationFileName: String
) {

    class Builder(private val specificationFileName: String) {
        var path: String = "/"

        fun build(): SwaggerUIConfiguration {
            return SwaggerUIConfiguration(
                path = path,
                specificationFileName = specificationFileName
            )
        }
    }
}
