/*
 * Stub for standalone build. Real implementation is in Android tools analytics.
 */
package com.android.tools.analytics

object CommonMetricsData {
    val javaProcessStats: Any? get() = null
    val jvmDetails: Any? get() = null
    val osArchitecture: String get() = System.getProperty("os.arch", "unknown")
}
