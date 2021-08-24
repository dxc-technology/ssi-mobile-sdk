package com.dxc.ssi.agent.kermit

expect class LogcatLogger constructor() : Logger {

    override fun log(
        severity: Severity,
        message: String,
        tag: String,
        throwable: Throwable?
    )

    override fun v(message: String, tag: String, throwable: Throwable?)
    override fun d(message: String, tag: String, throwable: Throwable?)
    override fun i(message: String, tag: String, throwable: Throwable?)
    override fun w(message: String, tag: String, throwable: Throwable?)
    override fun e(message: String, tag: String, throwable: Throwable?)
    override fun wtf(message: String, tag: String, throwable: Throwable?)

}