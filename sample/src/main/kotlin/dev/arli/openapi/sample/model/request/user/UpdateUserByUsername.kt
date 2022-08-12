package dev.arli.openapi.sample.model.request.user

import dev.arli.openapi.annotation.Description
import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.RequestBody
import dev.arli.openapi.annotation.Response

data class UpdateUserByUsernameRequest(
    @Path(description = "Name that need to be deleted")
    val username: String,
    @Description("Update an existent user in the store")
    @RequestBody
    val updateUserByUsernameDto: UpdateUserByUsernameDto
)

@Response(description = "Successful operation")
object UpdateUserByUsernameResponse

data class UpdateUserByUsernameDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phone: String,
    val userStatus: Int
)
