package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.Contact
import dev.arli.openapi.model.ContactObject
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class ContactMapperTest {

    private val mapper = ContactMapper()

    @Test
    fun `Should map contact to contact object`() {
        val givenContact = Contact(
            name = "Contact",
            url = Url("http://localhost/contact"),
            email = "contact@mail.com"
        )

        val expectedContactObject = ContactObject(
            name = "Contact",
            url = Url("http://localhost/contact"),
            email = "contact@mail.com"
        )

        assertThat(mapper.map(givenContact)).isEqualTo(expectedContactObject)
    }
}
