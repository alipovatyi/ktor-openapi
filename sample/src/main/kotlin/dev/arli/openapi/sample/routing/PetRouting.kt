package dev.arli.openapi.sample.routing

import dev.arli.openapi.routing.documentedDelete
import dev.arli.openapi.routing.documentedGet
import dev.arli.openapi.routing.documentedPost
import dev.arli.openapi.routing.documentedPut
import dev.arli.openapi.sample.model.Pet
import dev.arli.openapi.sample.model.request.InvalidIdSuppliedResponse
import dev.arli.openapi.sample.model.request.InvalidInputResponse
import dev.arli.openapi.sample.model.request.InvalidPetIdValueResponse
import dev.arli.openapi.sample.model.request.InvalidStatusValueResponse
import dev.arli.openapi.sample.model.request.InvalidTagValueResponse
import dev.arli.openapi.sample.model.request.PetNotFoundResponse
import dev.arli.openapi.sample.model.request.ValidationExceptionResponse
import dev.arli.openapi.sample.model.request.pet.AddPetRequest
import dev.arli.openapi.sample.model.request.pet.AddPetResponse
import dev.arli.openapi.sample.model.request.pet.DeletePetRequest
import dev.arli.openapi.sample.model.request.pet.DeletePetResponse
import dev.arli.openapi.sample.model.request.pet.FindPetsByStatusRequest
import dev.arli.openapi.sample.model.request.pet.FindPetsByStatusResponse
import dev.arli.openapi.sample.model.request.pet.FindPetsByTagsRequest
import dev.arli.openapi.sample.model.request.pet.FindPetsByTagsResponse
import dev.arli.openapi.sample.model.request.pet.GetPetByIdRequest
import dev.arli.openapi.sample.model.request.pet.GetPetByIdResponse
import dev.arli.openapi.sample.model.request.pet.UpdatePetFormRequest
import dev.arli.openapi.sample.model.request.pet.UpdatePetFormResponse
import dev.arli.openapi.sample.model.request.pet.UpdatePetRequest
import dev.arli.openapi.sample.model.request.pet.UpdatePetResponse
import dev.arli.openapi.sample.model.request.pet.UploadPetImageRequest
import dev.arli.openapi.sample.model.request.pet.UploadPetImageResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.Routing
import io.ktor.server.routing.route

internal fun Routing.petRouting() = route("/pet") {
    authenticate("oauth2") {
        documentedPut<UpdatePetRequest, UpdatePetResponse>(
            path = "/",
            responses = {
                response<UpdatePetResponse, Pet>(HttpStatusCode.OK)
                response<InvalidIdSuppliedResponse>(HttpStatusCode.BadRequest)
                response<PetNotFoundResponse>(HttpStatusCode.NotFound)
                response<ValidationExceptionResponse>(HttpStatusCode.MethodNotAllowed)
            }
        ) {}
    }

    authenticate("oauth2") {
        documentedPost<AddPetRequest, AddPetResponse>(
            path = "/",
            responses = {
                response<AddPetResponse, Pet>(HttpStatusCode.OK)
                response<InvalidInputResponse>(HttpStatusCode.MethodNotAllowed)
            }
        ) {}
    }

    authenticate("oauth2") {
        documentedGet<FindPetsByStatusRequest, FindPetsByStatusResponse>(
            path = "/findByStatus",
            responses = {
                response<FindPetsByStatusResponse, List<Pet>>(HttpStatusCode.OK)
                response<InvalidStatusValueResponse>(HttpStatusCode.BadRequest)
            }
        ) {}
    }

    authenticate("oauth2") {
        documentedGet<FindPetsByTagsRequest, FindPetsByTagsResponse>(
            path = "/findByTags",
            responses = {
                response<FindPetsByTagsResponse, List<Pet>>(HttpStatusCode.OK)
                response<InvalidTagValueResponse>(HttpStatusCode.BadRequest)
            }
        ) {}
    }

    authenticate("oauth2", "apiKey") {
        documentedGet<GetPetByIdRequest, GetPetByIdResponse>(
            path = "/{petId}",
            responses = {
                response<GetPetByIdResponse, Pet>(HttpStatusCode.OK)
                response<InvalidIdSuppliedResponse>(HttpStatusCode.BadRequest)
                response<PetNotFoundResponse>(HttpStatusCode.NotFound)
            }
        ) {}
    }

    authenticate("oauth2") {
        documentedPost<UpdatePetFormRequest, UpdatePetFormResponse>(
            path = "/{petId}",
            responses = {
                response<InvalidInputResponse>(HttpStatusCode.MethodNotAllowed)
            }
        ) {}
    }

    authenticate("oauth2") {
        documentedDelete<DeletePetRequest, DeletePetResponse>(
            path = "/{petId}",
            responses = {
                response<InvalidPetIdValueResponse>(HttpStatusCode.BadRequest)
            }
        ) {}
    }

    authenticate("oauth2") {
        documentedPost<UploadPetImageRequest, UploadPetImageResponse>(
            path = "/{petId}/uploadImage",
            responses = {
                response<UploadPetImageResponse>(HttpStatusCode.OK)
            }
        ) {}
    }
}
