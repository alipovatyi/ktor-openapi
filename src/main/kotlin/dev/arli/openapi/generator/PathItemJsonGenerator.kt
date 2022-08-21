package dev.arli.openapi.generator

import dev.arli.openapi.model.PathItemObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal class PathItemJsonGenerator(
    private val operationJsonGenerator: OperationJsonGenerator = OperationJsonGenerator()
) {

    fun generatePathItemJson(pathItem: PathItemObject): JsonObject {
        return buildJsonObject {
            pathItem.summary?.let { summary ->
                put("summary", summary)
            }
            pathItem.description?.let { description ->
                put("description", description)
            }
            pathItem.get?.let { get ->
                put("get", operationJsonGenerator.generateOperationJson(get))
            }
            pathItem.put?.let { put ->
                put("put", operationJsonGenerator.generateOperationJson(put))
            }
            pathItem.post?.let { post ->
                put("post", operationJsonGenerator.generateOperationJson(post))
            }
            pathItem.delete?.let { delete ->
                put("delete", operationJsonGenerator.generateOperationJson(delete))
            }
        }
    }
}
