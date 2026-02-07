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
    base
    kotlin("jvm") version "1.9.24"
    id("java-gradle-plugin")
    `maven-publish`
}

apply(from = "../release_version.gradle")

group = "com.android.compose.screenshot"
version = project.version

base {
    archivesName.set("screenshot-test-gradle-plugin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

val generateVersionPropertiesResourceFile = tasks.register("generateVersionPropertiesResourceFile") {
    val resourceFile = layout.buildDirectory.file("generated/resources/com-android-compose-screenshot.properties")
    outputs.file(resourceFile)
    doLast {
        resourceFile.get().asFile.parentFile.mkdirs()
        resourceFile.get().asFile.writeText("buildVersion=$version\n")
    }
}

tasks.named("processResources").configure { dependsOn(generateVersionPropertiesResourceFile) }

sourceSets {
    main {
        resources {
            srcDir(layout.buildDirectory.dir("generated/resources"))
        }
    }
}

gradlePlugin {
    plugins {
        create("comAndroidComposeScreenshot") {
            id = "com.android.compose.screenshot"
            implementationClass = "com.android.compose.screenshot.PreviewScreenshotGradlePlugin"
        }
    }
}

dependencies {
    compileOnly(project(":screenshot:standalone-stubs"))
    compileOnly(libs.agp.gradle)
    implementation(libs.guava)
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.agp.gradle)
}

sourceSets {
    main.get().resources.srcDir("src/main/java")
    test.get().resources.srcDir("src/test/java")
}

publishing {
    publications {
        create<MavenPublication>("plugin") {
            from(components["java"])
            groupId = "com.android.compose.screenshot"
            artifactId = "screenshot-test-gradle-plugin"
            version = project.version.toString()
        }
    }
    repositories {
        maven {
            name = "localRepo"
            url = layout.buildDirectory.dir("repo").get().asFile.toURI()
        }
    }
}

tasks.register<Zip>("zipPlugin") {
    dependsOn("publishPluginPublicationToLocalRepoRepository")
    from(layout.buildDirectory.dir("repo"))
    archiveFileName.set("screenshot-test-gradle-plugin.zip")
    destinationDirectory.set(layout.buildDirectory.dir("dist"))
}
