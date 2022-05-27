plugins {
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.kotlin.dateTime)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.serialization)
    testImplementation(libs.bundles.kotlin.test)
    testImplementation(libs.ktor.server.test)
}
