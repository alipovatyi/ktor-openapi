package dev.arli.openapi.annotation

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Header(
    val name: String = "", // TODO: ignore "Accept", "Content-Type" and "Authorization"
    val description: String = "",
    val required: Boolean = false,
    val deprecated: Boolean = false
)
