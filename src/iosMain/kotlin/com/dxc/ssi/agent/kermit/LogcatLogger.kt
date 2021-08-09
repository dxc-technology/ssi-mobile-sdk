package com.dxc.ssi.agent.kermit

import platform.Foundation.NSLog
import platform.Foundation.NSThread

actual class LogcatLogger : Logger() {

    actual override fun log(
        severity: Severity,
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        NSLog("%s: (%s) %@ %s", severity.name, tag, NSThread.currentThread, message)
    }

    actual override fun v(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        log(Severity.Verbose, message, tag, throwable)
    }

    actual override fun d(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        log(Severity.Debug, message, tag, throwable)
    }

    actual override fun i(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        log(Severity.Info, message, tag, throwable)
    }

    actual override fun w(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        log(Severity.Warn, message, tag, throwable)
    }

    actual override fun e(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        log(Severity.Error, message, tag, throwable)
    }

    actual override fun wtf(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        log(Severity.Assert, message, tag, throwable)
    }

}