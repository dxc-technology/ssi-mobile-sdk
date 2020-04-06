package com.luxoft.blockchainlab.poc.ssi.ssimobile.utils

import java.util.*

/**
 * Remove after migrate to Android 26 and use [Instant]
 * */
data class InstantCompat(val millis: Long) {
    companion object {
        fun now(): InstantCompat = InstantCompat(Calendar.getInstance().getTime().time)
    }
}