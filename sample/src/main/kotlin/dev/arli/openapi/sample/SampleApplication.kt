package dev.arli.openapi.sample

import dev.arli.openapi.authentication.documentedBasic
import dev.arli.openapi.authentication.documentedImplicitOAuth2
import dev.arli.openapi.authentication.documentedJWT
import dev.arli.openapi.openAPIGen
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.http.HttpMethod
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.auth.OAuthServerSettings
import io.ktor.server.auth.authentication
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation as ClientContentNegotiation

fun main() {
    val json = Json {
        prettyPrint = true
        isLenient = true
    }
    val httpClient = HttpClient(CIO) {
        install(ClientContentNegotiation) {
            json(json)
        }
    }
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) { json(json) }
        authentication {
            documentedBasic("basic") {}
            documentedJWT("bearer") {}
            documentedImplicitOAuth2(
                name = "oauth2",
                authorizeUrl = Url("https://petstore3.swagger.io/oauth/authorize"),
                scopes = {
                    scope("write:pets") { description = "Modify pets in your account" }
                    scope("read:pets") { description = "Read your pets" }
                }
            ) {
                urlProvider = { "http://localhost:8080/callback" }
                providerLookup = {
                    OAuthServerSettings.OAuth2ServerSettings(
                        name = "oauth2",
                        authorizeUrl = "https://petstore3.swagger.io/oauth/authorize",
                        accessTokenUrl = "",
                        requestMethod = HttpMethod.Post,
                        clientId = "<client-id>",
                        clientSecret = "<client-secret>",
                        defaultScopes = listOf("write:pets", "read:pets")
                    )
                }
                client = httpClient
            }
        }
        openAPIGen {
            this.json = json
            info(
                title = "Swagger Petstore - OpenAPI 3.0",
                version = "1.0.11"
            ) {
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

                license(name = "Apache 2.0") {
                    url = Url("https://www.apache.org/licenses/LICENSE-2.0.html")
                }
            }

            servers {
                server(Url("/v3"))
            }

            tags {
                tag("pet") { description = "Everything about your Pets" }
                tag("store") { description = "Access to Petstore orders" }
                tag("user") { description = "Operations about user" }
            }

            swaggerUI {
                path = "/documentation"
            }
        }
        installRouting()
    }.start(wait = true)
}
