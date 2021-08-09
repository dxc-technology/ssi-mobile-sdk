package com.dxc.ssi.agent.kermit

actual class LogcatLogger : Logger() {
    actual override fun log(
        severity: Severity,
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
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