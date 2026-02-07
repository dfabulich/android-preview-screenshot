/*
 * Stub for standalone build. Real implementation is protobuf-generated.
 */
package com.google.wireless.android.sdk.stats

class GradleBuildProfileSpan private constructor() {
    enum class ExecutionType { TASK_EXECUTION_ALL_PHASES, UNKNOWN }
    var type: ExecutionType = ExecutionType.UNKNOWN
    var threadId: Long = 0L
    var startTimeInMs: Long = 0L
    var durationInMs: Long = 0L

    fun toByteArray(): ByteArray = ByteArray(0)

    class Builder {
        private val span = GradleBuildProfileSpan()
        fun setType(t: ExecutionType): Builder { span.type = t; return this }
        fun setThreadId(id: Long): Builder { span.threadId = id; return this }
        fun setStartTimeInMs(ms: Long): Builder { span.startTimeInMs = ms; return this }
        fun setDurationInMs(ms: Long): Builder { span.durationInMs = ms; return this }
        fun build(): GradleBuildProfileSpan = span
    }

    companion object {
        @JvmStatic
        fun newBuilder(): Builder = Builder()
    }
}
