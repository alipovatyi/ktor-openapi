plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    application
}

group = "dev.arli"
version = "0.0.1"

application {
    mainClass.set("dev.arli.openapi.sample.SampleApplicationKt")
}

dependencies {
    implementation(project(":"))
    implementation(kotlin("stdlib"))
    implementation(libs.kotlin.dateTime)
    implementation(libs.kotlin.serialization)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.contentNegotiation)
    implementation(libs.ktor.server.serialization)
    implementation(libs.ktor.server.auth.jvm)
    implementation(libs.logback)
}
