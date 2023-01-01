package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.OpenAPIGenConfiguration
import dev.arli.openapi.model.ComponentsObject
import dev.arli.openapi.model.HttpSecurityScheme
import dev.arli.openapi.model.Info
import dev.arli.openapi.model.InfoObject
import dev.arli.openapi.model.OpenAPIObject
import dev.arli.openapi.model.PathItemObject
import dev.arli.openapi.model.Server
import dev.arli.openapi.model.ServerObject
import dev.arli.openapi.model.ServerVariables
import dev.arli.openapi.model.Servers
import dev.arli.openapi.model.Tag
import dev.arli.openapi.model.TagObject
import dev.arli.openapi.model.Tags
import dev.arli.openapi.model.security.HttpSecuritySchemeType
import io.ktor.http.Url
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class OpenAPIMapperTest {

    private val mapper = OpenAPIMapper()

    @Test
    fun `Should map OpenAPI to OpenAPI object`() {
        val givenPathItems = mapOf("/pet" to PathItemObject())
        val givenSecuritySchemes = mapOf(
            "security-scheme" to HttpSecurityScheme(
                description = "Description",
                scheme = HttpSecuritySchemeType.BASIC
            )
        )
        val givenTags = Tags(listOf(Tag(name = "Tag", description = null, externalDocs = null)))

        val expectedOpenAPIObject = OpenAPIObject(
            openapi = "3.0.3",
            info = InfoObject(
                title = "Swagger Petstore - OpenAPI 3.0",
                version = "1.0.11"
            ),
            servers = listOf(ServerObject(url = Url("http://localhost/server"))),
            paths = givenPathItems,
            components = ComponentsObject(
                securitySchemes = givenSecuritySchemes
            ),
            tags = listOf(TagObject(name = "Tag"))
        )

        val actualOpenAPIObject = mapper.map(
            openAPIGenConfiguration = OpenAPIGenConfiguration(
                info = Info(
                    title = "Swagger Petstore - OpenAPI 3.0",
                    description = null,
                    termsOfService = null,
                    contact = null,
                    license = null,
                    version = "1.0.11"
                ),
                servers = Servers(
                    servers = listOf(
                        Server(
                            url = Url("http://localhost/server"),
                            description = null,
                            variables = ServerVariables()
                        )
                    )
                )
            ),
            pathItems = givenPathItems,
            securitySchemes = givenSecuritySchemes,
            tags = givenTags
        )

        assertThat(actualOpenAPIObject).isEqualTo(expectedOpenAPIObject)
    }

    @Test
    fun `Should throw exception if info is null`() {
        assertFailsWith<IllegalArgumentException> {
            mapper.map(
                openAPIGenConfiguration = OpenAPIGenConfiguration(
                    info = null,
                    servers = Servers()
                ),
                pathItems = emptyMap(),
                securitySchemes = emptyMap(),
                tags = Tags()
            )
        }
    }
}
