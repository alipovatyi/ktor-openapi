package dev.arli.openapi.sample.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: Long?,
    val petId: Long?,
    val shipDate: LocalDate?,
    val status: OrderStatus?,
    val complete: Boolean?
)
