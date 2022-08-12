package dev.arli.openapi.sample.model.request.pet

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Response

data class DeletePetRequest(
    @Path(description = "Pet id to delete")
    val petId: Long
)

@Response(description = "Successful operation")
object DeletePetResponse
