package dev.arli.openapi.sample.model.request.user

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.model.User
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.User],
    summary = "Get user by user name"
)
data class GetUserByUsernameRequest(
    @Path(description = "The name that needs to be fetched. Use user1 for testing.")
    val username: String
)

@Response(description = "Successful operation")
data class GetUserByUsernameResponse(
    @Content val user: User
)
