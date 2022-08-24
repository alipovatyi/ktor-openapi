package dev.arli.openapi.model

import io.ktor.http.Url

typealias ContactBuilder = Contact.Builder.() -> Unit

data class Contact(
    val name: String?,
    val url: Url?,
    val email: String?
) {

    class Builder {
        var name: String? = null
        var url: Url? = null
        var email: String? = null

        fun build(): Contact {
            return Contact(
                name = name,
                url = url,
                email = email
            )
        }
    }
}
