package com.aeroxmotion.asyncjsonfilestorage

import com.facebook.react.bridge.*
import kotlinx.coroutines.*

class AsyncJsonFileStorageModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext), CoroutineScope {
    override fun getName() = "AsyncJsonFileStorage"

    override val coroutineContext =
        Dispatchers.IO + CoroutineName("AsyncJsonFileStorageScope") + SupervisorJob()

    private val storage = JsonFileStorage(reactContext)

    @ReactMethod
    fun getAllKeys(name: String, promise: Promise) {
        launch(createExceptionHandler(promise)) {
            val keys = storage.getKeys(name)
            promise.resolve(keys.toNativeArray())
        }
    }

    @ReactMethod
    fun multiGet(name: String, keys: ReadableArray, promise: Promise) {
        launch(createExceptionHandler(promise)) {
            val values = storage.getValues(name, keys.toKeysList())
            promise.resolve(values.toNativeArray())
        }
    }

    @ReactMethod
    fun multiSet(name: String, entries: ReadableMap, promise: Promise) {
        launch(createExceptionHandler(promise)) {
            storage.setValues(name, entries.toEntriesMap())
            promise.resolve(null)
        }
    }

    @ReactMethod
    fun multiRemove(name: String, keys: ReadableArray, promise: Promise) {
        launch(createExceptionHandler(promise)) {
            storage.removeValues(name, keys.toKeysList())
            promise.resolve(null)
        }
    }

    @ReactMethod
    fun clear(name: String, promise: Promise) {
        launch(createExceptionHandler(promise)) {
            storage.clearValues(name)
            promise.resolve(null)
        }
    }
}
