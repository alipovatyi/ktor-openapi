plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.8.0"
    id("com.github.ben-manes.versions") version "0.44.0"
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
    id("com.diffplug.spotless") version "6.12.0"
    id("maven-publish")
}

allprojects {
    repositories {
        mavenCentral()
    }

    apply(plugin = "com.diffplug.spotless")
}

koverMerged {
    enable()

    filters {
        projects {
            excludes += listOf("sample")
        }
    }

    htmlReport {
        reportDir.set(layout.buildDirectory.dir("reports/kover"))
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

spotless {
    kotlin {
        ktlint().editorConfigOverride(mapOf("ktlint_disabled_rules" to "filename"))
        target("**/*.kt")
    }
    kotlinGradle {
        ktlint()
        target("*.gradle.kts")
    }
}

publishing {
    publications {
        register<MavenPublication>("maven") {
            groupId = "dev.arli"
            artifactId = "ktor-openapi"
            version = libs.versions.library.get()

            from(components["kotlin"])
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.kotlin.dateTime)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.serialization)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.swagger)
    testImplementation(libs.bundles.kotlin.test)
    testImplementation(libs.ktor.server.test)
    testImplementation(libs.truth)
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf { isNonStableDependency(candidate.version) }

    gradleReleaseChannel = "current"

    outputFormatter = closureOf<com.github.benmanes.gradle.versions.reporter.result.Result> {
        val markdown = StringBuilder().apply {
            if (outdated.dependencies.isNotEmpty()) {
                append("### Available dependency updates")
                appendLine()
                append("|Dependency|Current version|New version|")
                appendLine()
                append("|--|--|--|")
                appendLine()
                outdated.dependencies.forEach { outdatedDependency ->
                    with(outdatedDependency) {
                        append("|")
                        append(if (projectUrl != null) "[$group:$name]($projectUrl)" else "$group:$name")
                        append("|")
                        append(version)
                        append("|")
                        append(available.release ?: available.milestone)
                        append("|")
                        appendLine()
                    }
                }
            } else {
                append("### All dependencies are up-to-date")
                appendLine()
            }
        }.toString()

        project.file(outputDir).mkdirs()

        File(outputDir, "$reportfileName.txt").let(project::file).writeText(markdown)
    }
}

fun isNonStableDependency(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
