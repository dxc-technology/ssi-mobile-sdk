package com.dxc.utils

import java.io.File
import java.io.FilePermission
import java.io.IOException
import java.security.AccessController

actual class FileUtils {
    actual companion object {
        private const val importantMessage = "please check phone's access rights: SDK <= 29 android.permission.WRITE_EXTERNAL_STORAGE "+
                "or SDK = 30 android.permission.MANAGE_EXTERNAL_STORAGE and ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION"

        actual fun fileExists(filePath: String): Boolean {
            return File(filePath).exists()
        }

        actual fun deleteRecursively(filePath: String) {
            File(filePath).deleteRecursively()
        }

        actual fun createFileWithContent(filePath: String, content: String) {
            val file = File(filePath)

            val actions = "read,write"
            try {
                AccessController.checkPermission(FilePermission(filePath, actions))
            } catch (e: Exception) {
                throw IOException("You don't have read/write permission to use: $filePath, $importantMessage" )
            }

            try {
                file.createNewFile()
            } catch (e: Exception) {
                throw IOException("Couldn't create a file in dir $filePath, $importantMessage")

            }

            val indyHomePath = "$filePath/.indy_client"
            try {
                AccessController.checkPermission(FilePermission(indyHomePath, actions))
            } catch (e: Exception) {
                throw IOException("You don't have read/write permission to use: $indyHomePath, $importantMessage")
            }
            file.writeText(content)
        }
    }
}