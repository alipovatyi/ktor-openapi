package dev.arli.openapi.mapper

import dev.arli.openapi.model.Info
import dev.arli.openapi.model.InfoObject

internal class InfoMapper(
    private val contactMapper: ContactMapper = ContactMapper(),
    private val licenseMapper: LicenseMapper = LicenseMapper()
) {

    fun map(info: Info): InfoObject {
        return InfoObject(
            title = info.title,
            description = info.description,
            termsOfService = info.termsOfService,
            contact = info.contact?.let(contactMapper::map),
            license = info.license?.let(licenseMapper::map),
            version = info.version
        )
    }
}
