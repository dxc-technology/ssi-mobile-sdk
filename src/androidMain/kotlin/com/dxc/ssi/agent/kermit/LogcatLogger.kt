package com.dxc.ssi.agent.kermit

import android.util.Log

actual class LogcatLogger : Logger() {

    private fun getSeverity(severity: Severity): Int {
        return when (severity) {
            Severity.Verbose -> 2
            Severity.Debug -> 3
            Severity.Info -> 4
            Severity.Warn -> 5
            Severity.Error -> 6
            Severity.Assert -> 7
        }
    }

    actual override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
            Log.println(getSeverity(severity), tag,
                "${Thread.currentThread()}: $message"
            )
            throwable?.let {
                Log.println(
                    getSeverity(severity),
                    tag,
                    PlatformThrowableStringProvider().getThrowableString(it)
                )
            }
    }

    actual override fun v(message: String, tag: String, throwable: Throwable?) {
        Log.v(tag, message, throwable)
    }

    actual override fun d(message: String, tag: String, throwable: Throwable?) {
        Log.d(tag, message, throwable)
    }

    actual override fun i(message: String, tag: String, throwable: Throwable?) {
        Log.i(tag, message, throwable)
    }

    actual override fun w(message: String, tag: String, throwable: Throwable?) {
        Log.w(tag, message, throwable)
    }

    actual override fun e(message: String, tag: String, throwable: Throwable?) {
        Log.e(tag, message, throwable)
    }

    actual override fun wtf(message: String, tag: String, throwable: Throwable?) {
        Log.wtf(tag, message, throwable)
    }
}