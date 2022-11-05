package dev.arli.openapi.sample.routing

import dev.arli.openapi.routing.documentedDelete
import dev.arli.openapi.routing.documentedGet
import dev.arli.openapi.routing.documentedPost
import dev.arli.openapi.routing.documentedPut
import dev.arli.openapi.sample.model.User
import dev.arli.openapi.sample.model.request.InvalidUsernameOrPasswordSuppliedResponse
import dev.arli.openapi.sample.model.request.InvalidUsernameSuppliedResponse
import dev.arli.openapi.sample.model.request.UserNotFoundResponse
import dev.arli.openapi.sample.model.request.user.CreateUserRequest
import dev.arli.openapi.sample.model.request.user.CreateUserResponse
import dev.arli.openapi.sample.model.request.user.CreateUsersRequest
import dev.arli.openapi.sample.model.request.user.CreateUsersResponse
import dev.arli.openapi.sample.model.request.user.DeleteUserRequest
import dev.arli.openapi.sample.model.request.user.DeleteUserResponse
import dev.arli.openapi.sample.model.request.user.GetUserByUsernameRequest
import dev.arli.openapi.sample.model.request.user.GetUserByUsernameResponse
import dev.arli.openapi.sample.model.request.user.LogInRequest
import dev.arli.openapi.sample.model.request.user.LogInResponse
import dev.arli.openapi.sample.model.request.user.LogOutRequest
import dev.arli.openapi.sample.model.request.user.LogOutResponse
import dev.arli.openapi.sample.model.request.user.UpdateUserByUsernameRequest
import dev.arli.openapi.sample.model.request.user.UpdateUserByUsernameResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Routing
import io.ktor.server.routing.route

internal fun Routing.userRouting() = route("/user") {
    documentedPost<CreateUserRequest, CreateUserResponse>(
        path = "/",
        responses = {
            defaultResponse<CreateUserResponse, User>()
        }
    ) {}
    documentedPost<CreateUsersRequest, CreateUsersResponse>(
        path = "/createWithList",
        responses = {
            response<CreateUsersResponse, List<User>>(HttpStatusCode.OK)
        }
    ) {}
    documentedGet<LogInRequest, LogInResponse>(
        path = "/login",
        responses = {
            response<LogInResponse, String>(HttpStatusCode.OK)
            response<InvalidUsernameOrPasswordSuppliedResponse, String>(HttpStatusCode.BadRequest)
        }
    ) {}
    documentedGet<LogOutRequest, LogOutResponse>(
        path = "/logout",
        responses = {
            defaultResponse<LogOutResponse>()
        }
    ) {}
    documentedGet<GetUserByUsernameRequest, GetUserByUsernameResponse>(
        path = "/{username}",
        responses = {
            response<GetUserByUsernameResponse, User>(HttpStatusCode.OK)
            response<InvalidUsernameSuppliedResponse, User>(HttpStatusCode.BadRequest)
            response<UserNotFoundResponse, User>(HttpStatusCode.NotFound)
        }
    ) {}
    documentedPut<UpdateUserByUsernameRequest, UpdateUserByUsernameResponse>(
        path = "/{username}",
        responses = {
            defaultResponse<UpdateUserByUsernameResponse>()
        }
    ) {}
    documentedDelete<DeleteUserRequest, DeleteUserResponse>(
        path = "/{username}",
        responses = {
            response<InvalidUsernameSuppliedResponse>(HttpStatusCode.BadRequest)
            response<UserNotFoundResponse>(HttpStatusCode.NotFound)
        }
    ) {}
}
