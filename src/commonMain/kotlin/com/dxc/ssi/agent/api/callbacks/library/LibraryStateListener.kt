package com.dxc.ssi.agent.api.callbacks.library

interface LibraryStateListener {
    fun initializationCompleted()
    fun initializationFailed(error: LibraryError, message: String? = null, details: String? = null, stackTrace: String? = null)
}