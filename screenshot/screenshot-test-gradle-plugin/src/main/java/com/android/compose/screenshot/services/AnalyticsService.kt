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

package com.android.compose.screenshot.services

import com.android.compose.screenshot.services.AnalyticsService.Params
import org.gradle.api.provider.Property
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters

/**
 * Build service used by screenshot tasks. The upstream implementation sends telemetry to the
 * Android Gradle plugin (usage stats, task timing). We stub it out: no telemetry is sent.
 */
abstract class AnalyticsService : BuildService<Params> {

  interface Params : BuildServiceParameters {
    val androidGradlePluginVersion: Property<String>
  }

  fun recordPreviewScreenshotTestRun(totalTestCount: Int) {
    // No-op: no telemetry
  }

  fun recordTaskAction(taskPath: String, block: () -> Unit) {
    block()
  }
}
