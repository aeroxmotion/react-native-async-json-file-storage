package com.aeroxmotion.asyncjsonfilestorage

import com.facebook.react.bridge.*

@Suppress("UNCHECKED_CAST")
fun ReadableArray.toKeysList(): List<String> {
    val keys = toArrayList()

    for (key in keys) {
        if (key == null) {
            throw Error("Key cannot be null")
        }

        if (key !is String) {
            throw Error("Invalid key type: ${key.javaClass.name}")
        }
    }

    return keys as ArrayList<String>
}

@Suppress("UNCHECKED_CAST")
fun ReadableMap.toEntriesMap(): Map<String, Any?> {
    return toHashMap()
}

fun Iterator<*>.toNativeArray(): WritableNativeArray {
    return Arguments.makeNativeArray(listOf(this))
}