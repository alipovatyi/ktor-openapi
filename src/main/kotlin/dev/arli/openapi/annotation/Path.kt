package dev.arli.openapi.annotation

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Path(
    val name: String = "",
    val description: String = "",
    val deprecated: Boolean = false
)
