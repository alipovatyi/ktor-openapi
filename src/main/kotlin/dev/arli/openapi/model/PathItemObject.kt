package dev.arli.openapi.model

internal data class PathItemObject(
//    val ref: ReferenceObject? = null, // TODO
    val summary: String? = null,
    val description: String? = null,
    val get: OperationObject? = null,
    val put: OperationObject? = null,
    val post: OperationObject? = null,
    val delete: OperationObject? = null
//    val options: OperationObject? = null, // TODO
//    val head: OperationObject? = null, // TODO
//    val patch: OperationObject? = null, // TODO
//    val trace: OperationObject? = null, // TODO
//    val servers: List<ServerObject> = emptyList(), // TODO
//    val parameters: List<ParameterComponent> = emptyList() // TODO
)
