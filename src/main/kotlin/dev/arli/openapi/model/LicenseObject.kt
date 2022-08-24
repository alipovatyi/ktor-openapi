package dev.arli.openapi.model

import io.ktor.http.Url

internal data class LicenseObject(
    var name: String, // REQUIRED
    var url: Url? = null
)
