package dev.arli.openapi.sample.routing

import dev.arli.openapi.routing.documentedDelete
import dev.arli.openapi.routing.documentedGet
import dev.arli.openapi.routing.documentedPost
import dev.arli.openapi.sample.model.Order
import dev.arli.openapi.sample.model.request.InvalidIdSuppliedResponse
import dev.arli.openapi.sample.model.request.InvalidInputResponse
import dev.arli.openapi.sample.model.request.OrderNotFoundResponse
import dev.arli.openapi.sample.model.request.store.AddOrderRequest
import dev.arli.openapi.sample.model.request.store.AddOrderResponse
import dev.arli.openapi.sample.model.request.store.DeleteOrderRequest
import dev.arli.openapi.sample.model.request.store.DeleteOrderResponse
import dev.arli.openapi.sample.model.request.store.GetInventoryRequest
import dev.arli.openapi.sample.model.request.store.GetInventoryResponse
import dev.arli.openapi.sample.model.request.store.GetOrderByIdRequest
import dev.arli.openapi.sample.model.request.store.GetOrderByIdResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.Routing
import io.ktor.server.routing.route

internal fun Routing.storeRouting() = route("/store") {
    authenticate("apiKey") {
        documentedGet<GetInventoryRequest, GetInventoryResponse>(
            path = "/inventory",
            tags = setOf(Tags.Store),
            summary = "Returns pet inventories by status",
            description = "Returns a map of status codes to quantities",
            responses = {
                response<GetInventoryResponse, Map<String, Int>>(HttpStatusCode.OK)
            }
        ) {}
    }
    documentedPost<AddOrderRequest, AddOrderResponse>(
        path = "/order",
        tags = setOf(Tags.Store),
        summary = "Place an order for a pet",
        description = "Place a new order in the store",
        responses = {
            response<AddOrderResponse, Order>(HttpStatusCode.OK)
            response<InvalidInputResponse, Order>(HttpStatusCode.MethodNotAllowed)
        }
    ) {}
    documentedGet<GetOrderByIdRequest, GetOrderByIdResponse>(
        path = "/order/{orderId}",
        tags = setOf(Tags.Store),
        summary = "Find purchase order by ID",
        description = "For valid response try integer IDs with value <= 5 or > 10. Other values will generate exceptions.",
        responses = {
            response<GetOrderByIdResponse, Order>(HttpStatusCode.OK)
            response<InvalidIdSuppliedResponse>(HttpStatusCode.BadRequest)
            response<OrderNotFoundResponse>(HttpStatusCode.NotFound)
        }
    ) {}
    documentedDelete<DeleteOrderRequest, DeleteOrderResponse>(
        path = "/order/{orderId}",
        tags = setOf(Tags.Store),
        summary = "Delete purchase order by ID",
        description = "For valid response try integer IDs with value < 1000. Anything above 1000 or non-integers will generate API errors",
        responses = {
            response<InvalidIdSuppliedResponse>(HttpStatusCode.BadRequest)
            response<OrderNotFoundResponse>(HttpStatusCode.NotFound)
        }
    ) {}
}
