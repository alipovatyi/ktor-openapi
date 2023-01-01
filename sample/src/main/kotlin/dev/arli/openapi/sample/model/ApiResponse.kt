package dev.arli.openapi.sample.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val code: Int?,
    val type: String?,
    val message: String?
)
