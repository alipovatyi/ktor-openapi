package dev.arli.openapi.sample.model.request.pet

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Query
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.Pet

data class FindPetsByTagsRequest(
    @Query(description = "Tags to filter by")
    val tags: List<String>
)

@Response(description = "Successful operation")
data class FindPetsByTagsResponse(
    @Content val content: List<Pet>
)
