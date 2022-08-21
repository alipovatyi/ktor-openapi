package dev.arli.openapi.mapper

import dev.arli.openapi.model.Example
import dev.arli.openapi.model.ExampleComponent
import dev.arli.openapi.model.ExampleObject

internal class ExampleMapper {

    fun <T> map(example: Example<T>): ExampleComponent {
        return ExampleObject(
            summary = example.summary,
            description = example.description,
            value = example.value,
            externalValue = null, // TODO
            valueJson = example.valueJson
        )
    }
}
