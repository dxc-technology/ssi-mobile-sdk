package com.dxc.utils

//TODO: think if standard kotlin Result can replace it
sealed class Result<out T : Any> {

        data class Success<out T : Any>(val data: T) : Result<T>()
        data class Error(val throwable: Throwable) : Result<Nothing>()

        override fun toString(): String {
            return when (this) {
                is Success<*> -> "Success[data=$data]"
                is Error -> "Error[throwable=$throwable]"
            }
        }
    }