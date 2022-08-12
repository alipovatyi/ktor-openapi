package dev.arli.openapi.sample.model.request.store

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Response

object GetInventoryRequest

@Response(description = "Successful operation")
data class GetInventoryResponse(
    @Content val content: Map<String, Int>
)
