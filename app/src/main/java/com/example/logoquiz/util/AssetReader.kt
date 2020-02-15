package com.example.logoquiz.util

import android.content.Context
import com.example.logoquiz.game.Logo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.Closeable
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

private const val LOGO_FILE_NAME = "logos.json"

interface AssetReader {
    fun readGameAssets(): List<Logo>
}

class AssetReaderImpl(private val context: Context): AssetReader {
    private val gson: Gson = Gson()

    override fun readGameAssets(): List<Logo> {
        var bufferReader: BufferedReader? = null
        return try {
            bufferReader = readDataFromAssets(LOGO_FILE_NAME)
            gson.fromJson(bufferReader, object : TypeToken<List<Logo>>() {}.type)
        } catch (ignored: Exception) {
            listOf()
        } finally {
            safeCloseIO(bufferReader)
        }
    }

    private fun readDataFromAssets(fileName: String): BufferedReader? {
        return try {
            val inputStream: InputStream? = context.assets.open(fileName)
            if (inputStream != null) BufferedReader(InputStreamReader(inputStream)) else null
        } catch (ignored: IOException) {
            null
        }
    }

    private fun safeCloseIO(closable: Closeable?) {
        try {
            closable?.close()
        } catch (ignored: IOException) {

        }
    }
}