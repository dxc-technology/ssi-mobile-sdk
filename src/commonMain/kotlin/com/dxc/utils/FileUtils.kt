package com.dxc.utils

expect class FileUtils {
    companion object {
        fun fileExists(filePath: String): Boolean
        fun deleteRecursively(filePath: String)
    }
}