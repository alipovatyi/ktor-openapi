plugins {
    kotlin("jvm") version "1.6.21"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.serialization)
    testImplementation(kotlin("test-junit5"))
}
