package dev.arli.openapi.annotation

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Header(
    val name: String = "",
    val required: Boolean = false
)
