package dev.arli.openapi.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Request(
    val tags: Array<String> = [],
    val summary: String = "",
    val description: String = "",
    val operationId: String = "",
    val deprecated: Boolean = false
)
