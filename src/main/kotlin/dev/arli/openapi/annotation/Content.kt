package dev.arli.openapi.annotation

import dev.arli.openapi.model.MediaType

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Content(val mediaType: MediaType = MediaType.APPLICATION_JSON)
