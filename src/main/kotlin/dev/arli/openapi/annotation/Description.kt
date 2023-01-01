package dev.arli.openapi.annotation

// TODO: consider using description parameter for each annotation separately
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Description(val value: String)
