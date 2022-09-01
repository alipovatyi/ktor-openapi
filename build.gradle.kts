plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("com.github.ben-manes.versions") version "0.42.0"
    id("org.jetbrains.kotlinx.kover") version "0.5.1"
    id("com.diffplug.spotless") version "6.10.0"
}

allprojects {
    repositories {
        mavenCentral()
    }

    apply(plugin = "com.diffplug.spotless")
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

spotless {
    kotlin {
        ktlint().editorConfigOverride(mapOf("disabled_rules" to "filename"))
        target("**/*.kt")
    }
    kotlinGradle {
        ktlint()
        target("*.gradle.kts")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.kotlin.dateTime)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.serialization)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.webjars)
    implementation(libs.ktor.server.htmlBuilder)
    implementation(libs.swaggerUi)
    testImplementation(libs.bundles.kotlin.test)
    testImplementation(libs.ktor.server.test)
    testImplementation(libs.truth)
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf { isNonStableDependency(candidate.version) }

    gradleReleaseChannel = "current"

    outputFormatter = closureOf<com.github.benmanes.gradle.versions.reporter.result.Result> {
        if (outdated.dependencies.isNotEmpty()) {
            val markdown = StringBuilder().apply {
                append("|Dependency|Old version|New version|")
                append("\n")
                append("|--|--|--|")
                append("\n")
                outdated.dependencies.forEach { outdatedDependency ->
                    with(outdatedDependency) {
                        append("|")
                        append(if (projectUrl != null) "[$group:$name]($projectUrl)" else "$group:$name")
                        append("|")
                        append(version)
                        append("|")
                        append(available.release ?: available.milestone)
                        append("|")
                        append("\n")
                    }
                }
            }.toString()

            project.file(outputDir).mkdirs()

            File(outputDir, "$reportfileName.txt").let(project::file).writeText(markdown)
        }
    }
}

fun isNonStableDependency(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
