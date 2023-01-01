package dev.arli.openapi.model

enum class MediaType(val key: String) {
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    APPLICATION_FORM("application/x-www-form-urlencoded"),
    APPLICATION_PDF("application/pdf"),
    MULTIPART_FORM("multipart/form-data"),
    IMG_PNG("image/png"),
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html")
}
