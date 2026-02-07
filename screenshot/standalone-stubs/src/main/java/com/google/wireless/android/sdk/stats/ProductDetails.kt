/*
 * Stub for standalone build. Real implementation is protobuf-generated.
 */
package com.google.wireless.android.sdk.stats

class ProductDetails private constructor() {
    enum class ProductKind { GRADLE, UNKNOWN }

    class Builder {
        var product: ProductKind = ProductKind.UNKNOWN
        var version: String = ""
        var osArchitecture: String = ""
        fun build(): ProductDetails = ProductDetails()
    }
}
