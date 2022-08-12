package dev.arli.openapi.sample.model.request.store

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.Order

data class GetOrderByIdRequest(
    @Path(description = "ID of order that needs to be fetched")
    val orderId: Long
)

@Response(description = "Successful operation")
data class GetOrderByIdResponse(
    @Content val order: Order
)
