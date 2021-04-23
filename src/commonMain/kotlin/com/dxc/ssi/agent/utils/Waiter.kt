package com.dxc.ssi.agent.utils

import kotlinx.coroutines.Deferred

expect class Waiter {
    companion object {
        fun <T> waitForCompletion(deferred: Deferred<T>):T
    }
}
