package com.dxc.utils

expect class FileUtils {
    companion object {
        fun fileExists(filePath: String): Boolean
        fun deleteRecursively(filePath: String)
        fun createFileWithContent(filePath: String, content: String)
    }
}