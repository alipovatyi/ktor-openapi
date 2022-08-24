package dev.arli.openapi.model

import io.ktor.http.Url

internal data class ContactObject(
    var name: String? = null,
    var url: Url? = null,
    var email: String? = null
)
