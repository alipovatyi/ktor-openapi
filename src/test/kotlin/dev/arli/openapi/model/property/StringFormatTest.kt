package dev.arli.openapi.model.property

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.reflect.KType
import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import java.time.LocalDate as JavaLocalDate
import java.time.LocalDateTime as JavaLocalDateTime
import kotlinx.datetime.LocalDate as KotlinLocalDate
import kotlinx.datetime.LocalDateTime as KotlinLocalDateTime

internal class StringFormatTest {

    @ParameterizedTest
    @MethodSource
    fun `Should return correct key for string format`(givenStringFormat: StringFormat, expectedKey: String) {
        assertEquals(expectedKey, givenStringFormat.key)
    }

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

    private companion object {

        @JvmStatic
        fun `Should return correct key for string format`() = listOf(
            arguments(StringFormat.NO_FORMAT, ""),
            arguments(StringFormat.DATE, "date"),
            arguments(StringFormat.DATE_TIME, "date-time"),
            arguments(StringFormat.PASSWORD, "password"),
            arguments(StringFormat.BYTE, "byte"),
            arguments(StringFormat.BINARY, "binary")
        )

        @JvmStatic
        fun `Should return string format`() = listOf(
            arguments(typeOf<String>(), StringFormat.NO_FORMAT),
            arguments(typeOf<String?>(), StringFormat.NO_FORMAT),
            arguments(typeOf<JavaLocalDate>(), StringFormat.DATE),
            arguments(typeOf<JavaLocalDate?>(), StringFormat.DATE),
            arguments(typeOf<JavaLocalDateTime>(), StringFormat.DATE_TIME),
            arguments(typeOf<JavaLocalDateTime?>(), StringFormat.DATE_TIME),
            arguments(typeOf<KotlinLocalDate>(), StringFormat.DATE),
            arguments(typeOf<KotlinLocalDate?>(), StringFormat.DATE),
            arguments(typeOf<KotlinLocalDateTime>(), StringFormat.DATE_TIME),
            arguments(typeOf<KotlinLocalDateTime?>(), StringFormat.DATE_TIME)
        )
    }
}
