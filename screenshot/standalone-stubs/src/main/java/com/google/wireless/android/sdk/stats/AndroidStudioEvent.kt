/*
 * Stub for standalone build. Real implementation is protobuf-generated.
 */
package com.google.wireless.android.sdk.stats

class AndroidStudioEvent private constructor() {
    enum class EventCategory { TESTS, UNKNOWN }
    enum class EventKind { TEST_RUN, UNKNOWN }

    fun toByteArray(): ByteArray = ByteArray(0)

    class Builder {
        var category: EventCategory = EventCategory.UNKNOWN
        var kind: EventKind = EventKind.UNKNOWN
        var javaProcessStats: Any? = null
        var jvmDetails: Any? = null
        val productDetailsBuilder: ProductDetails.Builder = ProductDetails.Builder()
        val testRunBuilder: TestRun.Builder = TestRun.Builder()
        fun build(): AndroidStudioEvent = AndroidStudioEvent()
    }

    companion object {
        @JvmStatic
        fun newBuilder(): Builder = Builder()
    }
}
