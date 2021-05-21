package com.aeroxmotion.asyncjsonfilestorage

import com.facebook.react.bridge.ReactApplicationContext
import org.json.JSONObject
import java.io.File

open class JsonFile(
    private val reactContext: ReactApplicationContext
) {
    fun <T> accessJsonFile(fileName: String, accessor: (jsonObject: JSONObject) -> T): T {
        return accessor(getJsonObject(getJsonFile(fileName)))
    }

    fun writeToJsonFile(fileName: String, modifier: (jsonObject: JSONObject) -> Unit) {
        val jsonFile = getJsonFile(fileName)
        val jsonObject = getJsonObject(jsonFile)

        modifier(jsonObject)

        jsonFile.writeText(jsonObject.toString())
    }

    private fun getJsonFile(fileName: String): File {
        val filePath = reactContext.filesDir.path
        val jsonFile = File(filePath, "${fileName}.json")

        if (!jsonFile.exists()) {
            // Make directories
            File(filePath).mkdirs()

            // Make file
            jsonFile.createNewFile()
        }

        return jsonFile
    }

    private fun getJsonObject(jsonFile: File): JSONObject {
        val fileContents = jsonFile.readText()

        return JSONObject(
            if (fileContents.isEmpty())
                "{}"
            else
                fileContents
        )
    }
}
