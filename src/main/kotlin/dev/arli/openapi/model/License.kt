package dev.arli.openapi.model

import io.ktor.http.Url

typealias LicenseBuilder = License.Builder.() -> Unit

data class License(
    val name: String,
    val url: Url?
) {

    class Builder(private val name: String) {
        var url: Url? = null

        fun build(): License {
            return License(
                name = name,
                url = url
            )
        }
    }
}
