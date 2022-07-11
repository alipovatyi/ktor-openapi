package dev.arli.openapi.generator

import dev.arli.openapi.model.ResponseObject
import kotlin.test.assertEquals
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test

// TODO
internal class ResponseJsonGeneratorTest {

    private val generator = ResponseJsonGenerator()

    @Test
    fun `Should convert response object to json object`() {
        val givenResponseObject = ResponseObject(
            description = "Successful operation",
            headers = mapOf(),
            content = mapOf(),
            links = mapOf()
        )

        val expectedJsonObject = buildJsonObject {
            put("description", "")
            putJsonObject("headers") {}
            putJsonObject("content") {}
            putJsonObject("links") {}
        }

        assertEquals(expectedJsonObject, generator.generateResponseJson(givenResponseObject))
    }

    @Test
    fun `Should convert reference object to json object`() {

    }
}

/*
"responses": {
  "200": {
    "description": "Successful operation",
    "content": {
      "application/json": {
        "schema": {
          "type": "object",
          "nullable": false,
          "properties": {
            "data": {
              "type": "object",
              "nullable": false,
              "properties": {
                "category": {
                  "type": "object",
                  "nullable": true,
                  "properties": {
                    "id": {
                      "type": "integer",
                      "format": "int64",
                      "nullable": true
                    },
                    "name": {
                      "type": "string",
                      "format": "",
                      "nullable": true
                    }
                  }
                },
                "id": {
                  "type": "integer",
                  "format": "int64",
                  "nullable": true
                },
                "name": {
                  "type": "string",
                  "format": "",
                  "nullable": false
                },
                "photoUrls": {
                  "type": "array",
                  "nullable": false,
                  "items": {
                    "type": "object",
                    "nullable": false,
                    "properties": {
                      "alt": {
                        "type": "string",
                        "format": "",
                        "nullable": true
                      },
                      "url": {
                        "type": "string",
                        "format": "",
                        "nullable": false
                      }
                    }
                  }
                },
                "status": {
                  "type": "string",
                  "nullable": true,
                  "enum": [
                    "available",
                    "pending",
                    "sold"
                  ]
                },
                "tags": {
                  "type": "array",
                  "nullable": true,
                  "items": {
                    "type": "object",
                    "nullable": false,
                    "properties": {
                      "id": {
                        "type": "integer",
                        "format": "int64",
                        "nullable": true
                      },
                      "name": {
                        "type": "string",
                        "format": "",
                        "nullable": true
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  },
  "404": {
    "description": "Not found",
    "content": {
      "application/json": {
        "schema": {
          "type": "object",
          "nullable": false,
          "properties": {
            "error": {
              "type": "object",
              "nullable": false,
              "properties": {
                "message": {
                  "type": "string",
                  "format": "",
                  "nullable": false
                }
              }
            }
          }
        }
      }
    }
  }
}
 */