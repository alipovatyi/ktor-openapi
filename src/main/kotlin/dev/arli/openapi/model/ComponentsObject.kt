package dev.arli.openapi.model

internal data class ComponentsObject(
//    val schemas: Map<String, SchemaComponent>, // TODO
//    val responses: Map<String, ResponseComponent>, // TODO
//    val parameters: Map<String, ParameterComponent>, // TODO
//    val examples: Map<String, ExampleComponent>, // TODO
//    val requestBodies: Map<String, RequestBodyComponent>, // TODO
//    val headers: Map<String, HeaderComponent>, // TODO
    val securitySchemes: Map<String, SecuritySchemeComponent>,
//    val links: Map<String, LinkComponent>, // TODO
//    val callbacks: Map<String, CallbackComponent> // TODO
)
