package dev.arli.openapi.mapper

import dev.arli.openapi.model.Example
import dev.arli.openapi.model.ExampleComponent
import dev.arli.openapi.model.ExampleObject

class ExampleMapper {

    fun map(example: Example): ExampleComponent {
        return ExampleObject(
            summary = example.summary,
            description = example.description,
            value = example.value,
            externalValue = null // TODO
        )
    }
}
