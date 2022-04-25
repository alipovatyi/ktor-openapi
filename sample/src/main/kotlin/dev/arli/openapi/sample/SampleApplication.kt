package dev.arli.openapi.sample

import dev.arli.openapi.OpenAPIGen
import io.ktor.http.Url
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
                title = "Swagger Petstore - OpenAPI 3.0"
                version = "1.0.11"
                description = """
                    This is a sample Pet Store Server based on the OpenAPI 3.0 specification. You can find out more
                    about Swagger at [https://swagger.io](https://swagger.io). In the third iteration of the pet store,
                    we've switched to the design first approach!
                    You can now help us improve the API whether it's by making changes to the definition itself or to 
                    the code.
                    That way, with time, we can improve the API in general, and expose some of the new features in OAS3.
                    
                    Some useful links:
                    - [The Pet Store repository](https://github.com/swagger-api/swagger-petstore)
                    - [The source API definition for the Pet Store](https://github.com/swagger-api/swagger-petstore/blob/master/src/main/resources/openapi.yaml)
                """.trimIndent()
                termsOfService = Url("https://swagger.io/terms/")

                contact {
                    email = "apiteam@swagger.io"
                }

                license {
                    name = "Apache 2.0"
                    url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
                }
            }
        }
    }.start(wait = true)
}
