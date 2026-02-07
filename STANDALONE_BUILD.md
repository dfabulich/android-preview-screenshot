# Standalone build for screenshot-test-gradle-plugin

This directory can be built **without** the full Android/tools/base monorepo.

## What is included

- **screenshot-test-gradle-plugin** – The Gradle plugin for Compose Preview screenshot testing. Builds and tests.
- **screenshot-validation-api** – API library for screenshot testing. Builds.
- **screenshot-validation-junit-engine** – JUnit 5 test engine that runs screenshot tests and uses the renderer. Builds against `com.android.tools.compose:compose-preview-renderer` from Maven (same version as `screenshotPluginVersion`). Uses Kotlin 2.1 so it can read the renderer JAR’s metadata. When the fork is consumed via `includeBuild`, this engine is used instead of the published one so you can apply fixes (e.g. add kotlin-reflect to the renderer classpath).
- **screenshot:standalone-stubs** – Stub implementations for `com.android.utils.FileUtils`, `com.android.SdkConstants`, `com.android.tools.analytics.CommonMetricsData`, and analytics protos. Used only at compile time; at runtime the plugin runs inside a project that has the real AGP and tools on the classpath.

Note: **compose-preview-detector** is not published to Maven; the engine does not depend on it. The renderer JAR is sufficient for the engine to compile and run.

## Requirements

- **JDK 17**
- **Gradle 8.10+** (or use the included wrapper)

## Build commands

From the repo root (this directory):

```bash
# Build everything (stubs, validation-api, plugin)
./gradlew build

# Build only the plugin
./gradlew :screenshot:screenshot-test-gradle-plugin:build

# Run plugin tests
./gradlew :screenshot:screenshot-test-gradle-plugin:test

# Build a distributable zip (Maven repo layout) into
#   screenshot/screenshot-test-gradle-plugin/build/dist/screenshot-test-gradle-plugin.zip
./gradlew :screenshot:screenshot-test-gradle-plugin:zipPlugin

# Publish plugin to local Maven (~/.m2/repository)
./gradlew :screenshot:screenshot-test-gradle-plugin:publishPluginPublicationToMavenLocal
```

## Version

Plugin version is taken from `gradle.properties` (`screenshotPluginVersion`, default `0.0.1-dev`). The same values are in `screenshot/release_version.bzl` for consistency with the Bazel build when used in the full repo.

## Layout

- `settings.gradle.kts` – Includes the three standalone subprojects.
- `build.gradle.kts` – Root project; sets version and repositories.
- `gradle/libs.versions.toml` – Version catalog (AGP, JUnit, Mockito, etc.).
- `screenshot/release_version.gradle` – Shared version logic; supports both full-repo paths and standalone (root `screenshotPluginVersion` or `screenshot/release_version.bzl`).
- `screenshot/standalone-stubs/` – Stub sources for tools/analytics and SDK classes so the plugin compiles without the full monorepo.
