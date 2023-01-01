package dev.arli.openapi.sample.model.request.pet

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Query
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.Pet
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.Pet],
    summary = "Find Pets by tags",
    description = "Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing."
)
data class FindPetsByTagsRequest(
    @Query(description = "Tags to filter by")
    val tags: List<String>
)

@Response(description = "Successful operation")
data class FindPetsByTagsResponse(
    @Content val content: List<Pet>
)
