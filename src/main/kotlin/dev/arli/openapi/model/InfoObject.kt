package dev.arli.openapi.model

import io.ktor.http.Url

internal data class InfoObject(
    var title: String, // REQUIRED
    var description: String? = null,
    var termsOfService: Url? = null,
    var contact: ContactObject? = null,
    var license: LicenseObject? = null,
    var version: String // REQUIRED
)
