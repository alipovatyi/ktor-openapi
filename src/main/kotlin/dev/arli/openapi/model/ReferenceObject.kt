package dev.arli.openapi.model

@JvmInline
internal value class ReferenceObject(
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
