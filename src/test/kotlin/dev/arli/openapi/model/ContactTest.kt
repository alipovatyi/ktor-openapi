package dev.arli.openapi.model

import com.google.common.truth.Truth.assertThat
import io.ktor.http.Url
import org.junit.jupiter.api.Test

internal class ContactTest {

    @Test
    fun `Should create contact`() {
        val expectedContact = Contact(
            name = "Contact",
            url = Url("http://localhost/contact"),
            email = "contact@mail.com"
        )

        val actualContact = Contact.Builder().apply {
            name = "Contact"
            url = Url("http://localhost/contact")
            email = "contact@mail.com"
        }.build()

        assertThat(actualContact).isEqualTo(expectedContact)
    }

    @Test
    fun `Should create contact with default values`() {
        val expectedContact = Contact(
            name = null,
            url = null,
            email = null
        )

        val actualContact = Contact.Builder().build()

        assertThat(actualContact).isEqualTo(expectedContact)
    }
}
