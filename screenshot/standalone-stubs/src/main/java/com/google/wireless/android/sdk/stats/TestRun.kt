/*
 * Stub for standalone build. Real implementation is protobuf-generated.
 */
package com.google.wireless.android.sdk.stats

class TestRun private constructor() {
    enum class TestInvocationType { GRADLE_TEST, UNKNOWN }
    enum class TestKind { PREVIEW_SCREENSHOT_TEST, UNKNOWN }

    class Builder {
        var testInvocationType: TestInvocationType = TestInvocationType.UNKNOWN
        var testKind: TestKind = TestKind.UNKNOWN
        var gradleVersion: String = ""
        var numberOfTestsExecuted: Int = 0
        fun build(): TestRun = TestRun()
    }
}
