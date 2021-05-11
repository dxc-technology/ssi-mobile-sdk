package com.dxc.utils

import java.io.File

actual class FileUtils {
    actual companion object {
        actual fun fileExists(filePath: String): Boolean {
            return File(filePath).exists()
        }

        actual fun deleteRecursively(filePath: String) {
            File(filePath).deleteRecursively()
        }
    }
}