package com.dxc.ssi.agent.kermit

import org.apache.log4j.Level
import org.apache.log4j.Logger as myLogger

actual class LogcatLogger : Logger() {
    private val logger: myLogger = myLogger.getLogger("myLogger")

    private fun getSeverity(severity: Severity): Level {
        return when (severity) {
            Severity.Verbose -> Level.TRACE
            Severity.Debug -> Level.DEBUG
            Severity.Info -> Level.INFO
            Severity.Warn -> Level.WARN
            Severity.Error -> Level.ERROR
            Severity.Assert -> Level.FATAL
        }
    }

    actual override fun log(
        severity: Severity,
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        logger.log(tag, getSeverity(severity), message, throwable)
    }

    actual override fun v(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        logger.log(tag, Level.TRACE, message, throwable)
    }

    actual override fun d(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        logger.log(tag, Level.DEBUG, message, throwable)
    }

    actual override fun i(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        logger.log(tag, Level.INFO, message, throwable)
    }

    actual override fun w(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        logger.log(tag, Level.WARN, message, throwable)
    }

    actual override fun e(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        logger.log(tag, Level.ERROR, message, throwable)
    }

    actual override fun wtf(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        logger.log(tag, Level.FATAL, message, throwable)
    }

}