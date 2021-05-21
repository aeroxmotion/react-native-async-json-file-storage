package com.aeroxmotion.asyncjsonfilestorage

import com.facebook.react.bridge.Promise
import kotlinx.coroutines.CoroutineExceptionHandler

internal fun createExceptionHandler(promise: Promise): CoroutineExceptionHandler {
    return CoroutineExceptionHandler { _, throwable ->
        promise.reject(throwable)
    }
}
