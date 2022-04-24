val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

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
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
}
