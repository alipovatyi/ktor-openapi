package dev.arli.openapi.model

import io.ktor.http.HttpStatusCode

data class OperationObject(
    val tags: Set<TagObject> = emptySet(), // TODO
    val summary: String? = null,
    val description: String? = null,
    val externalDocs: ExternalDocumentationObject? = null,
    val operationId: String? = null,
    val parameters: List<ParameterComponent> = emptyList(),
    val requestBody: RequestBodyComponent? = null,
    val responses: Map<HttpStatusCode?, ResponseComponent> = emptyMap(), // REQUIRED
//    val callbacks: Map<String, CallbackComponent> = emptyMap(), // TODO
    val deprecated: Boolean = false,
//    val security: List<SecurityRequirementObject> = emptyList(), // TODO
//    val servers: List<ServerObject> = emptyList() // TODO
)
