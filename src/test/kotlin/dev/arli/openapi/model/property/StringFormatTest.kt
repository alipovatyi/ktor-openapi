package dev.arli.openapi.model.property

import kotlin.reflect.KType
import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class StringFormatTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return string format`(givenType: KType, expectedStringFormat: StringFormat) {
        assertEquals(expectedStringFormat, givenType.getStringFormat())
    }

    @Test
    fun `Should throw exception for unsupported string type`() {
        val givenType = typeOf<Int>()

        assertFailsWith<IllegalArgumentException> {
            givenType.getStringFormat()
        }
    }

    private companion object{

        @JvmStatic
        fun `Should return string format`() = listOf(
            arguments(typeOf<String>(), StringFormat.NO_FORMAT),
            arguments(typeOf<String?>(), StringFormat.NO_FORMAT),
            arguments(typeOf<LocalDate>(), StringFormat.DATE),
            arguments(typeOf<LocalDate?>(), StringFormat.DATE),
            arguments(typeOf<LocalDateTime>(), StringFormat.DATE_TIME),
            arguments(typeOf<LocalDateTime?>(), StringFormat.DATE_TIME)
        )
    }
}
