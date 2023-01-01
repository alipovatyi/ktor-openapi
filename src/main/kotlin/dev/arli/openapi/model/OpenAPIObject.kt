package dev.arli.openapi.model

internal data class OpenAPIObject(
    val openapi: String, // REQUIRED
    val info: InfoObject, // REQUIRED
    val servers: List<ServerObject>,
    val paths: Map<String, PathItemObject>, // REQUIRED
    val components: ComponentsObject,
//    val security: List<SecurityRequirementObject>, // TODO
    val tags: List<TagObject>
//    val externalDocs: ExternalDocumentationObject? // TODO
)
