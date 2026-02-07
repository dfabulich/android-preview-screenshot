/*
 * Stub for standalone build. Real implementation is in Android tools.
 */
package com.android.utils

import java.io.File

object FileUtils {
    @JvmStatic
    fun cleanOutputDir(dir: File) {
        if (dir.isDirectory) {
            dir.listFiles()?.forEach { it.deleteRecursively() }
        }
    }

    @JvmStatic
    fun copyFile(source: File, dest: File) {
        source.copyTo(dest, overwrite = true)
    }
}
