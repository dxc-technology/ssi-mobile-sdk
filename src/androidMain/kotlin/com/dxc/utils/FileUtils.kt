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

        actual fun createFileWithContent(filePath: String, content: String) {
            val file = File(filePath)
            file.createNewFile()
            file.writeText(content)
        }
    }
}