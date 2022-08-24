package dev.arli.openapi.model

import io.ktor.http.Url

typealias InfoBuilder = Info.Builder.() -> Unit

data class Info(
    val title: String,
    val description: String?,
    val termsOfService: Url?,
    val contact: Contact?,
    val license: License?,
    val version: String
) {

    class Builder(
        private val title: String,
        private val version: String
    ) {
        var description: String? = null
        var termsOfService: Url? = null
        var contact: Contact? = null
        var license: License? = null

        inline fun contact(builder: ContactBuilder) {
            contact = Contact.Builder().apply(builder).build()
        }

        inline fun license(name: String, builder: LicenseBuilder = {}) {
            license = License.Builder(name = name).apply(builder).build()
        }

        fun build(): Info {
            return Info(
                title = title,
                description = description,
                termsOfService = termsOfService,
                contact = contact,
                license = license,
                version = version
            )
        }
    }
}
