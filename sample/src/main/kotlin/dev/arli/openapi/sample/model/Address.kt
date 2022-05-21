package dev.arli.openapi.sample.model

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val street: String?,
    val city: String?,
    val state: String?,
    val zip: String?
)
