package dev.arli.openapi.sample.model.request.user

import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.User],
    summary = "Logs out current logged in user session"
)
object LogOutRequest

@Response(description = "Successful operation")
object LogOutResponse
