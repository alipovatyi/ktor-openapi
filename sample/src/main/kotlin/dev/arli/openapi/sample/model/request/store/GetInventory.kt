package dev.arli.openapi.sample.model.request.store

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.Store],
    summary = "Returns pet inventories by status",
    description = "Returns a map of status codes to quantities"
)
object GetInventoryRequest

@Response(description = "Successful operation")
data class GetInventoryResponse(
    @Content val content: Map<String, Int>
)
