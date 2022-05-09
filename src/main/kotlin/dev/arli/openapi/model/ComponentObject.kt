package dev.arli.openapi.model

sealed interface ComponentObject

interface SchemaComponent : ComponentObject

interface ResponseComponent : ComponentObject

interface ParameterComponent : ComponentObject

interface ExampleComponent : ComponentObject

interface RequestBodyComponent : ComponentObject

interface HeaderComponent : ComponentObject

interface SecuritySchemeComponent : ComponentObject

interface LinkComponent : ComponentObject

interface CallbackComponent : ComponentObject
