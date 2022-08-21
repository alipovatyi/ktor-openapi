package dev.arli.openapi.model.security

data class OAuthFlowsObject(
//    val implicit: OAuthFlowObject?, // TODO
//    val password: OAuthFlowObject?, // TODO
//    val clientCredentials: OAuthFlowObject?, // TODO
    val authorizationCode: OAuthFlowObject?
)
