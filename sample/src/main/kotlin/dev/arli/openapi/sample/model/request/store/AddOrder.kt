package dev.arli.openapi.sample.model.request.store

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.RequestBody
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.Order
import dev.arli.openapi.sample.model.PetStatus
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

data class AddOrderRequest(
    @RequestBody val addOrderDto: AddOrderDto
)

@Response(description = "Successful operation")
data class AddOrderResponse(
    @Content val content: Order
)

@Serializable
data class AddOrderDto(
    val id: Long,
    val petId: Long,
    val quantity: Int,
    val shipDate: LocalDateTime,
    val status: PetStatus,
    val complete: Boolean
)
