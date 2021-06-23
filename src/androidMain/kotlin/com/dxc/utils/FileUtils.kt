package com.dxc.utils

import java.io.File
import java.io.IOException

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
            try {
                file.createNewFile()
            } catch (e: IOException) {
                println("Couldn't create a file in dir $filePath, cause ${e.message}, please check phone access rights")
                return
            }
            file.writeText(content)
        }
    }
}