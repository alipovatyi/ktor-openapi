package dev.arli.openapi.sample.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class OrderStatus {
    @SerialName("placed")
    PLACED,
    @SerialName("approved")
    APPROVED,
    @SerialName("delivered")
    DELIVERED
}
