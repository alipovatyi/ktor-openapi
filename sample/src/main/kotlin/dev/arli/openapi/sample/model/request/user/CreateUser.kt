package dev.arli.openapi.sample.model.request.user

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Description
import dev.arli.openapi.annotation.RequestBody
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.User

data class CreateUserRequest(
    @Description("Created user object")
    @RequestBody
    val createUserDto: CreateUserDto
)

@Response(description = "Successful operation")
data class CreateUserResponse(
    @Content val content: User
)

data class CreateUserDto(
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phone: String,
    val userStatus: Int
)
