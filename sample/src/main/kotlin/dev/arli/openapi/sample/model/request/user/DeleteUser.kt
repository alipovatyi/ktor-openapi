package dev.arli.openapi.sample.model.request.user

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.User],
    summary = "Delete user",
    description = "This can only be done by the logged in user."
)
data class DeleteUserRequest(
    @Path(description = "The name that needs to be deleted")
    val username: String
)

@Response(description = "Successful operation")
object DeleteUserResponse
