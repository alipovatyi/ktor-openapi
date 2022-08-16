package dev.arli.openapi.swagger

import io.ktor.server.html.Template
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.title
import kotlinx.html.unsafe

class SwaggerUI(
    private val swaggerUIConfiguration: SwaggerUIConfiguration
) : Template<HTML> {

    override fun HTML.apply() {
        val specificationFileName = swaggerUIConfiguration.specificationFileName
        val webjarsPath = swaggerUIConfiguration.webjarsPath
        val oauth2RedirectPath = swaggerUIConfiguration.oauth2RedirectPath
        head {
            title = "Swagger UI"
            meta(charset = "UTF-8")
            link(rel = "stylesheet", type = "text/css", href = "/$webjarsPath/swagger-ui/swagger-ui.css")
            link(rel = "icon", type = "image/png", href = "/$webjarsPath/swagger-ui/favicon-32x32.png") {
                sizes = "32x32"
            }
            link(rel = "icon", type = "image/png", href = "/$webjarsPath/swagger-ui/favicon-16x16.png") {
                sizes = "16x16"
            }
            unsafe {
                +"""
                    <style>
                        html
                        {
                            box-sizing: border-box;
                            overflow: -moz-scrollbars-vertical;
                            overflow-y: scroll;
                        }

                        *,
                        *:before,
                        *:after
                        {
                            box-sizing: inherit;
                        }

                        body
                        {
                            margin:0;
                            background: #fafafa;
                        }
                    </style>
                """.trimIndent()
            }
        }
        body {
            div { id = "swagger-ui" }
            script(src = "/$webjarsPath/swagger-ui/swagger-ui-bundle.js") {
                charset = "UTF-8"
            }
            script(src = "/$webjarsPath/swagger-ui/swagger-ui-standalone-preset.js") {
                charset = "UTF-8"
            }
            unsafe {
                +"""
                    <script>
                        window.onload = function() {
                            // Begin Swagger UI call region
                            const ui = SwaggerUIBundle({
                                url: "$specificationFileName",
                                oauth2RedirectUrl: window.location.protocol + "//" + window.location.host + "/" + "${oauth2RedirectPath.removePrefix("/")}",
                                dom_id: '#swagger-ui',
                                deepLinking: true,
                                presets: [
                                    SwaggerUIBundle.presets.apis,
                                    SwaggerUIStandalonePreset
                                ],
                                plugins: [
                                    SwaggerUIBundle.plugins.DownloadUrl
                                ],
                                layout: "StandaloneLayout"
                            });
                            // End Swagger UI call region

                            window.ui = ui;
                        };
                    </script>
                """.trimIndent()
            }
        }
    }
}
