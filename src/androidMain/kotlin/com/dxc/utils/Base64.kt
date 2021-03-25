package com.dxc.utils

import java.util.Base64

actual class Base64 {
    actual companion object {
        actual fun base64StringToPlainString(base64String: String): String {
            return String(Base64.getMimeDecoder().decode(base64String))
        }

        actual fun plainStringToBase64String(plainString: String): String {
            return Base64.getEncoder().encodeToString(plainString.toByteArray())
        }


    }
}