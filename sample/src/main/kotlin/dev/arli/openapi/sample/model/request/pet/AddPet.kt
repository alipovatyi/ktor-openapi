package dev.arli.openapi.sample.model.request.pet

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.RequestBody
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.Pet
import dev.arli.openapi.sample.model.PetStatus
import kotlinx.serialization.Serializable

data class AddPetRequest(
    @RequestBody(required = true)
    val addPetDto: AddPetDto
)

@Response(description = "Successful operation")
data class AddPetResponse(
    @Content val content: Pet
)

@Serializable
data class AddPetDto(
    val name: String,
    val categoryId: Long,
    val photoUrls: List<String>,
    val tagIds: List<Long>,
    val status: PetStatus
)
