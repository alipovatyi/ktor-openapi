package dev.arli.openapi.annotation

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Cookie(
    val name: String = "",
    val description: String = "",
    val required: Boolean = false,
    val deprecated: Boolean = false
)
