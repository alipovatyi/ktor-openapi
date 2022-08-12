package dev.arli.openapi.sample.model.request.user

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Response

data class DeleteUserRequest(
    @Path(description = "The name that needs to be deleted")
    val username: String
)

@Response(description = "Successful operation")
object DeleteUserResponse
