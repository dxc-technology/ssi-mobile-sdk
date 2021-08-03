package co.touchlab.kermit

import platform.Foundation.NSLog

actual class LogcatLogger : Logger() {

    actual override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        NSLog("%s: (%s) %s", severity.name, tag,  message)
    }

    actual override fun v(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
    }

    actual override fun d(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
    }

    actual override fun i(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
    }

    actual override fun w(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
    }

    actual override fun e(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
    }

    actual override fun wtf(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
    }

}