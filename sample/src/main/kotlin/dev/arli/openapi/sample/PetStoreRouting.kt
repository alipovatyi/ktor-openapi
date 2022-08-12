package dev.arli.openapi.sample

import dev.arli.openapi.sample.routing.petRouting
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.installRouting() = routing {
    petRouting()
}
