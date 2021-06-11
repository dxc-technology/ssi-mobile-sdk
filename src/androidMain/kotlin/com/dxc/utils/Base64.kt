package com.dxc.utils

import android.util.Base64

actual class Base64 {
    actual companion object {
        actual fun base64StringToPlainString(base64String: String): String {
            return String(Base64.decode(base64String, Base64.NO_WRAP))
        }

        actual fun plainStringToBase64String(plainString: String): String {
            return Base64.encodeToString(plainString.toByteArray(), Base64.NO_WRAP)
        }


    }
}