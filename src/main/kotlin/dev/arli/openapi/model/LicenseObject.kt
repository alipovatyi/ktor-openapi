package dev.arli.openapi.model

import io.ktor.http.Url

data class LicenseObject(
    var name: String? = null, // REQUIRED
    var url: Url? = null
)
