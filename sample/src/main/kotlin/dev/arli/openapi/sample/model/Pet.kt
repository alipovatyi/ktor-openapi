package dev.arli.openapi.sample.model

import kotlinx.serialization.Serializable

@Serializable
data class Pet(
    val id: Long?,
    val name: String,
    val category: Category?,
    val photoUrls: List<String>, // TODO: use Url
    val tags: List<Tag>?,
    val status: PetStatus?
)
