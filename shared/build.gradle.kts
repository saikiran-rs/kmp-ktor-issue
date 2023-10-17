val ktorVersion = "2.3.4"

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        tvosX64(),
        tvosArm64(),
        tvosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            }
        }

        val androidMain by getting {
            dependencies {
                dependsOn(commonMain)
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }

        val iosMain by getting {
            dependencies {
                dependsOn(commonMain)
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
        val tvosMain by getting {
            dependencies {
                dependsOn(commonMain)
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }

        val tvosSimulatorArm64Main by getting {
            dependsOn(tvosMain)
        }


        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.example.kmpsamplens"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
    }
}