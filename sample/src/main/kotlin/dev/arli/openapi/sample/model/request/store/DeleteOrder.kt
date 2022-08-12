package dev.arli.openapi.sample.model.request.store

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Response

data class DeleteOrderRequest(
    @Path(description = "ID of the order that needs to be deleted")
    val orderId: Long
)

@Response(description = "Successful operation")
object DeleteOrderResponse
