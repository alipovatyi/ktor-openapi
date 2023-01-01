package dev.arli.openapi.sample.model

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id: Long?,
    val username: String?,
    val address: Address?
)
