package dev.arli.openapi.model

@JvmInline
value class ReferenceObject(
    val value: String
) : SchemaComponent,
    ResponseComponent,
    ParameterComponent,
    ExampleComponent,
    RequestBodyComponent,
    HeaderComponent,
    SecuritySchemeComponent,
    LinkComponent,
    CallbackComponent
