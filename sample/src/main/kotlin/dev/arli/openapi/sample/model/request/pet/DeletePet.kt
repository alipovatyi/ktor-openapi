package dev.arli.openapi.sample.model.request.pet

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.Pet],
    summary = "Deletes a pet"
)
data class DeletePetRequest(
    @Path(description = "Pet id to delete")
    val petId: Long
)

@Response(description = "Successful operation")
object DeletePetResponse
