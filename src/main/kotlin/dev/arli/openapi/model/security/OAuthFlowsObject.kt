package dev.arli.openapi.model.security

internal data class OAuthFlowsObject(
    val implicit: OAuthFlowObject? = null,
//    val password: OAuthFlowObject? = null, // TODO
//    val clientCredentials: OAuthFlowObject? = null, // TODO
    val authorizationCode: OAuthFlowObject? = null
)
