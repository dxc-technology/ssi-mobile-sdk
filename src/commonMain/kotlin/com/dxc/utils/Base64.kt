package com.dxc.utils

expect class Base64 {
    companion object {
        fun base64StringToPlainString(base64String: String): String
        fun plainStringToBase64String(plainString: String): String
    }
}