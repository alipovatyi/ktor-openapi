package dev.arli.openapi.sample.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PetStatus {
    @SerialName("available")
    AVAILABLE,
    @SerialName("pending")
    PENDING,
    @SerialName("sold")
    SOLD
}
