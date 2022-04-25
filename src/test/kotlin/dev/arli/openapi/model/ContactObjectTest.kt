package dev.arli.openapi.model

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class ContactObjectTest {

    @Test
    fun `Should return contact with default values`() {
        val expectedContact = ContactObject(
            name = null,
            url = null,
            email = null
        )
        val actualContact = ContactObject()

        assertEquals(expectedContact, actualContact)
    }
}
