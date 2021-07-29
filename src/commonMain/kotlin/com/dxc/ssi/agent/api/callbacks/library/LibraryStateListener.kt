package com.dxc.ssi.agent.api.callbacks.library

interface LibraryStateListener {
    fun initializationCompleted()
    fun initializationFailed(error: LibraryError, details: String)
}