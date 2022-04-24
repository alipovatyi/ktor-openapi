plugins {
    kotlin("jvm")
    application
}

group = "dev.arli"
version = "0.0.1"

application {
    mainClass.set("dev.arli.openapi.sample.SampleApplicationKt")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback)
}
