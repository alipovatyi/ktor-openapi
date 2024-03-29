package dev.arli.openapi.sample.model.request.pet

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Description
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.RequestBody
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.Pet
import dev.arli.openapi.sample.model.PetStatus
import dev.arli.openapi.sample.routing.Tags
import kotlinx.serialization.Serializable

@Request(
    tags = [Tags.Pet],
    summary = "Update an existing pet",
    description = "Update an existing pet by Id"
)
data class UpdatePetRequest(
    @Description("Update an existent pet in the store")
    @RequestBody(required = true)
    val updatePetDto: UpdatePetDto
)

@Response(description = "Successful operation")
data class UpdatePetResponse(
    @Content val content: Pet
)

@Serializable
data class UpdatePetDto(
    val id: Long,
    val name: String,
    val categoryId: Long,
    val photoUrls: List<String>,
    val tagIds: List<Long>,
    val status: PetStatus
)
