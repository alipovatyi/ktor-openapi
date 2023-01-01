package dev.arli.openapi.sample.model.request.pet

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.Pet
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.Pet],
    summary = "Find pet by ID",
    description = "Returns a single pet"
)
data class GetPetByIdRequest(
    @Path(description = "ID of pet to return")
    val petId: Long
)

@Response(description = "Successful operation")
data class GetPetByIdResponse(
    @Content val content: Pet
)
