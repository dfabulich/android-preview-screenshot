plugins {
    base
}

val screenshotPluginVersion: String = rootProject.findProperty("screenshotPluginVersion") as String? ?: "0.0.1-dev"

allprojects {
    group = "com.android.compose.screenshot"
    version = screenshotPluginVersion

    repositories {
        google()
        mavenCentral()
    }
}
