package com.github.crazygit.demo.androidgistwithkotlin.network

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


object HttpUtil {

    fun sendHttpRequest(address: String, listener: HttpCallbackListener) {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpURLConnection
                val input = connection.run {
                    requestMethod = "GET"
                    connectTimeout = 8000
                    readTimeout = 8000
                    inputStream
                }
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                listener.onFinish(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                listener.onError(e)
            } finally {
                connection?.disconnect()
            }
        }
    }

    fun sendOkHttpRequest(adress: String, callback: okhttp3.Callback) {
        var client = OkHttpClient()
        var request = Request.Builder()
            .url(adress)
            .build()
        client.newCall(request).enqueue(callback)

    }

}