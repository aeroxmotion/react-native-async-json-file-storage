package com.aeroxmotion.asyncjsonfilestorage

import com.facebook.react.bridge.ReactApplicationContext

class JsonFileStorage(reactContext: ReactApplicationContext) : JsonFile(reactContext) {
    fun getKeys(storageName: String): Iterator<String> =
        accessJsonFile(storageName) { it.keys() }

    fun getValues(storageName: String, keys: List<String>): Iterator<Any?> =
        accessJsonFile(storageName) {
            val values = mutableListOf<Any?>()

            for (key in keys) {
                values.add(if (!it.has(key)) null else it.get(key))
            }

            values.iterator()
        }

    fun setValues(storageName: String, entries: Map<String, Any?>) =
        writeToJsonFile(storageName) {
            for ((key, value) in entries) {
                it.put(key, value)
            }
        }

    fun removeValues(storageName: String, keys: List<String>) =
        writeToJsonFile(storageName) {
            for (key in keys) {
                it.remove(key)
            }
        }

    fun clearValues(storageName: String) =
        writeToJsonFile(storageName) {
            for (key in it.keys()) {
                it.remove(key)
            }
        }
}
