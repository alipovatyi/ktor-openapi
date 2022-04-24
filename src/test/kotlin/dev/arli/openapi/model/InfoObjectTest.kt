package dev.arli.openapi.model

import io.ktor.http.Url
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class InfoObjectTest {

    @Test
    fun `Should return info with default values`() {
        val expectedInfo = InfoObject(
            title = null,
            description = null,
            termsOfService = null,
            contact = null,
            license = null,
            version = null
        )
        val actualInfo = InfoObject()

        assertEquals(expectedInfo, actualInfo)
    }

    @Test
    fun `Should update contact object`() {
        val expectedInfo = InfoObject(
            contact = ContactObject(
                name = "Contact",
                url = Url("http://localhost/contact"),
                email = "contact@mail.com"
            )
        )
        val actualInfo = InfoObject().apply {
            contact {
                name = "Contact"
                url = Url("http://localhost/contact")
                email = "contact@mail.com"
            }
        }

        assertEquals(expectedInfo, actualInfo)
    }

    @Test
    fun `Should update license object`() {
        val expectedInfo = InfoObject(
            license = LicenseObject(
                name = "License",
                url = Url("http://localhost/license")
            )
        )
        val actualInfo = InfoObject().apply {
            license {
                name = "License"
                url = Url("http://localhost/license")
            }
        }

        assertEquals(expectedInfo, actualInfo)
    }
}
