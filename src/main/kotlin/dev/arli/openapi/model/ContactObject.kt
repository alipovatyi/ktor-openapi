package dev.arli.openapi.model

import io.ktor.http.Url

data class ContactObject(
    var name: String? = null,
    var url: Url? = null,
    var email: String? = null
)
