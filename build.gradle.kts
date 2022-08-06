plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("org.jetbrains.kotlinx.kover") version "0.5.1"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

kover {
    coverageEngine.set(kotlinx.kover.api.CoverageEngine.JACOCO)
}

tasks.withType<Test> {
    useJUnitPlatform()

    extensions.configure(kotlinx.kover.api.KoverTaskExtension::class) {
        includes = listOf("dev.arli.openapi.*")
        excludes = listOf("dev.arli.openapi.sample.*")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.kotlin.dateTime)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.serialization)
    implementation(libs.ktor.server.auth.jvm)
    testImplementation(libs.bundles.kotlin.test)
    testImplementation(libs.ktor.server.test)
    testImplementation(libs.truth)
}
