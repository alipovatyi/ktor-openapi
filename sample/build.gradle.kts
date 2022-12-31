plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    application
}

group = "dev.arli"
version = libs.versions.library.get()

application {
    mainClass.set("dev.arli.openapi.sample.SampleApplicationKt")
}

dependencies {
    implementation(project(":"))
    implementation(kotlin("stdlib"))
    implementation(libs.kotlin.dateTime)
    implementation(libs.kotlin.serialization)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.contentNegotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.contentNegotiation)
    implementation(libs.ktor.server.serialization)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.logback)
}
