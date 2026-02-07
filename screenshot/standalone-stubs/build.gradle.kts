plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm") version "1.9.24"
}

group = "com.android.compose.screenshot"
version = rootProject.findProperty("screenshotPluginVersion") as String? ?: "0.0.1-dev"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.7.2")
}
