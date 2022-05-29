package dev.arli.openapi.generator

import dev.arli.openapi.model.OperationObject
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject

// TODO convert parameters
// TODO convert request body
// TODO convert responses
class OperationJsonGenerator(
    private val externalDocumentationJsonGenerator: ExternalDocumentationJsonGenerator = ExternalDocumentationJsonGenerator(),
    private val parameterJsonGenerator: ParameterJsonGenerator = ParameterJsonGenerator(),
    private val requestBodyJsonGenerator: RequestBodyJsonGenerator = RequestBodyJsonGenerator(),
    private val responseJsonGenerator: ResponseJsonGenerator = ResponseJsonGenerator()
) {

    fun generateOperationJson(operation: OperationObject): JsonObject {
        return buildJsonObject {
            putJsonArray("tags") {
                operation.tags.forEach { tag -> add(tag.name) }
            }
            operation.summary?.let { summary ->
                put("summary", summary)
            }
            operation.description?.let { description ->
                put("description", description)
            }
            operation.externalDocs?.let { externalDocs ->
                put("externalDocs", externalDocumentationJsonGenerator.generateExternalDocumentationJson(externalDocs))
            }
            operation.operationId?.let { operationId ->
                put("operationId", operationId)
            }
            putJsonArray("parameters") {
                operation.parameters.forEach { parameter ->
                    add(parameterJsonGenerator.generateParameterJson(parameter))
                }
            }
            operation.requestBody?.let { requestBody ->
                put("request", requestBodyJsonGenerator.generateRequestBodyJson(requestBody))
            }
            putJsonObject("responses") {
                operation.responses.forEach { (statusCode, response) ->
                    put(statusCode.value.toString(), responseJsonGenerator.generateResponseJson(response))
                }
            }
            put("deprecated", operation.deprecated)
        }
    }
}
