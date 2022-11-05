package dev.arli.openapi.sample.model.request.store

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.Store],
    summary = "Delete purchase order by ID",
    description = "For valid response try integer IDs with value < 1000. Anything above 1000 or non-integers will generate API errors"
)
data class DeleteOrderRequest(
    @Path(description = "ID of the order that needs to be deleted")
    val orderId: Long
)

@Response(description = "Successful operation")
object DeleteOrderResponse
