package dev.arli.openapi.mapper

import dev.arli.openapi.model.Contact
import dev.arli.openapi.model.ContactObject

internal class ContactMapper {

    fun map(contact: Contact): ContactObject {
        return ContactObject(
            name = contact.name,
            url = contact.url,
            email = contact.email
        )
    }
}
