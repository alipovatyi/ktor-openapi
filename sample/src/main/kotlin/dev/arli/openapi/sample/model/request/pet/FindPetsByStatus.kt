package dev.arli.openapi.sample.model.request.pet

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Query
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.Pet
import dev.arli.openapi.sample.model.PetStatus
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.Pet],
    summary = "Find Pets by status",
    description = "Multiple status values can be provided with comma separated strings"
)
data class FindPetsByStatusRequest(
    @Query(description = "Status values that need to be considered for filter")
    val status: PetStatus
)

@Response(description = "Successful operation")
data class FindPetsByStatusResponse(
    @Content val content: List<Pet>
)
