/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    kotlin("jvm") version "2.1.0"  // 2.x required to read compose-preview-renderer JAR (Kotlin 2.2 metadata)
    `java-library`
    `maven-publish`
}

apply(from = "../release_version.gradle")

group = "com.android.tools.screenshot"
version = project.version

base {
    archivesName.set("screenshot-validation-junit-engine")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

val rendererVersion: String = rootProject.findProperty("screenshotRendererVersion") as String? ?: "0.0.1-alpha13"

dependencies {
    implementation(project(":screenshot:screenshot-validation-api"))
    implementation(libs.junit.platform.engine)
    implementation(libs.junit.platform.reporting)
    implementation(libs.junit.platform.commons)
    implementation(libs.asm)
    implementation("com.android.tools.compose:compose-preview-renderer:$rendererVersion")
    // compose-preview-detector is not published; renderer JAR may include detector classes
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.kotlin.test)
}

sourceSets {
    main.get().resources.srcDir("src/main/java")
    test.get().resources.srcDir("src/test/java")
}

publishing {
    publications {
        create<MavenPublication>("engine") {
            from(components["java"])
            groupId = "com.android.tools.screenshot"
            artifactId = "screenshot-validation-junit-engine"
            version = project.version.toString()
        }
    }
}
