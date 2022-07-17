package dev.arli.openapi.model

data class Examples(
    private val examples: Map<String, Example> = emptyMap()
) : Map<String, Example> by examples {

    private constructor(builder: Builder) : this(examples = builder.examples)

    data class Builder(val examples: MutableMap<String, Example> = mutableMapOf()) {

        inline fun example(
            name: String,
            value: Any,
            builder: Example.Builder.() -> Unit = {}
        ) {
            examples[name] = Example.example(value, builder)
        }

        fun build(): Examples {
            return Examples(this)
        }
    }

    companion object {
        inline fun examples(builder: Builder.() -> Unit): Examples {
            return Builder().apply(builder).build()
        }
    }
}