package dev.arli.openapi.sample.model.request.pet

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Query
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.Pet

data class UpdatePetFormRequest(
    @Path(description = "ID of pet that needs to be updated")
    val petId: Long,
    @Query(description = "Name of pet that needs to be updated")
    val name: String,
    @Query(description = "Status of pet that needs to be updated")
    val status: String
)

@Response(description = "Successful operation")
data class UpdatePetFormResponse(
    @Content val content: Pet
)
