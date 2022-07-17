package dev.arli.openapi.model

data class Example(
    val value: Any,
    val summary: String?,
    val description: String?
) {

    private constructor(builder: Builder) : this(
        value = builder.value,
        summary = builder.summary,
        description = builder.description
    )

    data class Builder(
        internal val value: Any,
        var summary: String? = null,
        var description: String? = null
    ) {
        fun build(): Example {
            return Example(this)
        }
    }

    companion object {
        inline fun example(value: Any, builder: Builder.() -> Unit = {}): Example {
            return Builder(value = value).apply(builder).build()
        }
    }
}
