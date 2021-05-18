package com.dxc.utils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ObjCObjectVar
import platform.Foundation.*

//TODO: find proper place for those functions
fun String.nsdata(): NSData? =
    NSString.create(string = this).dataUsingEncoding(NSUTF8StringEncoding)

fun NSData.string(): String? =
    NSString.create(data = this, encoding = NSUTF8StringEncoding)?.toString()

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

        actual fun createFileWithContent(filePath: String, content: String) {
            NSFileManager().createFileAtPath(filePath, content.nsdata(), null)
        }
    }
}