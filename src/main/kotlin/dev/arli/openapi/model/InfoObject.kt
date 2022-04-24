package dev.arli.openapi.model

import io.ktor.http.Url

data class InfoObject(
    var title: String? = null, // REQUIRED
    var description: String? = null,
    var termsOfService: Url? = null,
    var contact: ContactObject? = null,
    var license: LicenseObject? = null,
    var version: String? = null // REQUIRED
) {
    inline fun contact(crossinline configure: ContactObject.() -> Unit) {
        contact = ContactObject().apply(configure)
    }

    inline fun license(crossinline configure: LicenseObject.() -> Unit) {
        license = LicenseObject().apply(configure)
    }
}
