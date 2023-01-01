package dev.arli.openapi.sample.model.request.store

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.Order
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.Store],
    summary = "Find purchase order by ID",
    description = "For valid response try integer IDs with value <= 5 or > 10. Other values will generate exceptions."
)
data class GetOrderByIdRequest(
    @Path(description = "ID of order that needs to be fetched")
    val orderId: Long
)

@Response(description = "Successful operation")
data class GetOrderByIdResponse(
    @Content val order: Order
)
