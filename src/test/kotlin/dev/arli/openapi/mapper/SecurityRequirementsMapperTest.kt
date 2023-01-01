package dev.arli.openapi.mapper

import com.google.common.truth.Truth.assertThat
import dev.arli.openapi.model.SecurityRequirementObject
import io.ktor.server.auth.AuthenticationRouteSelector
import io.ktor.server.routing.PathSegmentConstantRouteSelector
import io.ktor.server.routing.RootRouteSelector
import io.ktor.server.routing.Route
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class SecurityRequirementsMapperTest {

    private val mapper = SecurityRequirementsMapper()

    @ParameterizedTest
    @MethodSource
    fun `Should return security requirement objects`(
        givenRoute: Route,
        expectedSecurityRequirementObjects: List<SecurityRequirementObject>
    ) {
        assertThat(mapper.map(givenRoute)).isEqualTo(expectedSecurityRequirementObjects)
    }

    private companion object {

        @JvmStatic
        fun `Should return security requirement objects`() = listOf(
            arguments(
                Route(selector = RootRouteSelector(), parent = null),
                emptyList<SecurityRequirementObject>()
            ),
            arguments(
                Route(
                    selector = RootRouteSelector(),
                    parent = Route(
                        selector = AuthenticationRouteSelector(names = listOf("bearer-auth")),
                        parent = null
                    )
                ),
                listOf(SecurityRequirementObject("bearer-auth"))
            ),
            arguments(
                Route(
                    selector = AuthenticationRouteSelector(names = listOf("basic-auth")),
                    parent = Route(
                        selector = PathSegmentConstantRouteSelector("path-1"),
                        parent = null
                    )
                ),
                listOf(SecurityRequirementObject("basic-auth"))
            ),
            arguments(
                Route(
                    selector = PathSegmentConstantRouteSelector("path-2"),
                    parent = Route(
                        selector = AuthenticationRouteSelector(names = listOf("basic-auth")),
                        parent = Route(
                            selector = PathSegmentConstantRouteSelector("path-1"),
                            parent = null
                        )
                    )
                ),
                listOf(SecurityRequirementObject("basic-auth"))
            ),
            arguments(
                Route(
                    selector = PathSegmentConstantRouteSelector("path-2"),
                    parent = Route(
                        selector = AuthenticationRouteSelector(names = listOf("basic-auth")),
                        parent = Route(
                            selector = PathSegmentConstantRouteSelector("path-1"),
                            parent = Route(
                                selector = AuthenticationRouteSelector(names = listOf("bearer-auth")),
                                parent = null
                            )
                        )
                    )
                ),
                listOf(
                    SecurityRequirementObject("basic-auth"),
                    SecurityRequirementObject("bearer-auth")
                )
            )
        )
    }
}
