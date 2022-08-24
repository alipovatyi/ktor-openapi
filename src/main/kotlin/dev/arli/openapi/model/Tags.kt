package dev.arli.openapi.model

typealias TagsBuilder = Tags.Builder.() -> Unit

data class Tags(
    private val tags: List<Tag> = emptyList()
) : List<Tag> by tags {

    class Builder {
        val tags: MutableList<Tag> = mutableListOf()

        inline fun tag(name: String, builder: TagBuilder = {}) {
            require(tags.none { it.name == name }) {
                "Tag name [$name] must be unique"
            }
            tags += Tag.Builder(name = name).apply(builder).build()
        }

        fun build(): Tags {
            return Tags(tags = tags.toList())
        }
    }
}
