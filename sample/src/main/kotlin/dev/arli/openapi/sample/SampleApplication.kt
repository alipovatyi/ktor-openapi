package dev.arli.openapi.sample

import dev.arli.openapi.OpenAPIGen
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        install(OpenAPIGen) {
            info {
                title = "Swagger Petstore"
                version = "0.0.1"
            }
        }
    }.start(wait = true)
}
