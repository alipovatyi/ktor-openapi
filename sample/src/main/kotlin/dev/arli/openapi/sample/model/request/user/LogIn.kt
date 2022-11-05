package dev.arli.openapi.sample.model.request.user

import dev.arli.openapi.annotation.Content
import dev.arli.openapi.annotation.Header
import dev.arli.openapi.annotation.Query
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.sample.routing.Tags

@Request(
    tags = [Tags.User],
    summary = "Logs user into the system"
)
data class LogInRequest(
    @Query(description = "The user name for login")
    val username: String,
    @Query(description = "The password for login in clear text")
    val password: String
)

@Response(description = "Successful operation")
data class LogInResponse(
    @Header(name = "X-Rate-Limit")
    val rateLimit: Int,
    @Header(name = "X-Expires-After")
    val expiresAfter: String,
    @Content
    val token: String
)
