package dev.arli.openapi.sample.model.request

import dev.arli.openapi.annotation.Response

@Response(description = "Invalid input")
object InvalidInputResponse

@Response(description = "Pet not found")
object PetNotFoundResponse

@Response(description = "Invalid ID supplied")
object InvalidIdSuppliedResponse

@Response(description = "Validation exception")
object ValidationExceptionResponse

@Response(description = "Invalid pet id value")
object InvalidPetIdValueResponse

@Response(description = "Invalid status value")
object InvalidStatusValueResponse

@Response(description = "Invalid tag value")
object InvalidTagValueResponse

@Response(description = "Order not found")
object OrderNotFoundResponse

@Response(description = "Invalid username supplied")
object InvalidUsernameSuppliedResponse

@Response(description = "User not found")
object UserNotFoundResponse

@Response(
    description = "\t\n" +
        "Invalid username/password supplied"
)
object InvalidUsernameOrPasswordSuppliedResponse
