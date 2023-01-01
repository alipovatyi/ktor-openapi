package dev.arli.openapi.sample.model.request.user

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Description
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.RequestBody
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.User
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.User],
    summary = "Create user",
    description = "This can only be done by the logged in user."
)
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
