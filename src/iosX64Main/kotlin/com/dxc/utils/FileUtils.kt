package com.dxc.utils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ObjCObjectVar
import platform.Foundation.NSError
import platform.Foundation.NSFileManager

actual class FileUtils {
    actual companion object {
        actual fun fileExists(filePath: String): Boolean {
            return NSFileManager().fileExistsAtPath(filePath)
        }

        actual fun deleteRecursively(filePath: String) {
            //TODO: add error handling and wrap error object into kotlin exception
            val error: CPointer<ObjCObjectVar<NSError?>>? = null
            NSFileManager().removeItemAtPath(filePath, error)
        }
    }
}