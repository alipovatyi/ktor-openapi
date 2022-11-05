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
    summary = "Creates list of users with given input array",
    description = "Creates list of users with given input array"
)
data class CreateUsersRequest(
    @Description("Created user object")
    @RequestBody
    val createUserDto: List<CreateUserDto>
)

@Response(description = "Successful operation")
data class CreateUsersResponse(
    @Content val content: List<User>
)
