package dev.arli.openapi.mapper

import dev.arli.openapi.model.License
import dev.arli.openapi.model.LicenseObject

internal class LicenseMapper {

    fun map(license: License): LicenseObject {
        return LicenseObject(
            name = license.name,
            url = license.url
        )
    }
}
