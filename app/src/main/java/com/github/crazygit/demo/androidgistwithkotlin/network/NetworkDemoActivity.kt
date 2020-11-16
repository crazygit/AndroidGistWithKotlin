package com.github.crazygit.demo.androidgistwithkotlin.network

import android.os.Bundle
import android.util.Log
import com.github.crazygit.demo.androidgistwithkotlin.BaseActivity
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityNetworkDemoBinding
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkDemoActivity : BaseActivity(), CoroutineScope by MainScope() {
    private val url = "https://httpbin.org/json"
    private val TAG = "NetworkDemoActivity"
    private lateinit var binding: ActivityNetworkDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendRequestWithHTTPURLConnectionBtn.setOnClickListener {
            sendRequestWithHTTPURLConnection()
        }
        binding.sendRequestWithOkHttpBtn.setOnClickListener {
            sendRequestWithOkHttp()
        }
        binding.sendRequestWithHTTPURLConnectionAndCallbackBtn.setOnClickListener {
            sendRequestWithHTTPURLConnectionAndCallBack()
        }
        binding.sendRequestWithOkHttpAndCallBackBtn.setOnClickListener {
            sendRequestWithOkHttpAndCallback()
        }

        binding.sendRequestWithRetrofit.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val requestService = retrofit.create(RequestService::class.java)
            requestService.getJsonData().enqueue(object : retrofit2.Callback<SlideShowResponse> {
                override fun onResponse(
                    call: retrofit2.Call<SlideShowResponse>,
                    response: Response<SlideShowResponse>
                ) {
                    //注意: retrofit里回调方法已经处于main线程当中，可以直接在这里操作UI
                    Log.d(TAG, "Current Thread: ${Thread.currentThread().name}")
                    val response = response.body()
                    response?.let {
                        Log.d(TAG, it.slideshow.title)
                        // 已经在主线程中，不需要使用runOnUiThread
//                        showResponse(it.slideshow.title)
                        binding.responseText.text = it.slideshow.title
                    } ?: Log.d(TAG, "response is empty")
                }

                override fun onFailure(call: retrofit2.Call<SlideShowResponse>, t: Throwable) {
                    t.printStackTrace()
//                    showResponse(t.message.toString())
                    binding.responseText.text = t.message.toString()
                }
            })
        }

        binding.sendRequestWithCoroutine.setOnClickListener {
            launch {
                val deferred = async {
                    getBaiduResponse()
                }
                binding.responseText.text = deferred.await()
            }
        }
    }

    private fun sendRequestWithHTTPURLConnectionAndCallBack() {
        HttpUtil.sendHttpRequest(
            url, object : HttpCallbackListener {

                //回调函数里如果要进行UI界面相关的操作，必须先切换到主线程
                override fun onFinish(response: String) {
                    showResponse(response)
                }

                override fun onError(e: Exception) {
                    showResponse(e.message.toString())
                }
            }
        )
    }

    private fun sendRequestWithHTTPURLConnection() {
        // 请求的时候要开启子线程，不能在主线程里发起网络请求
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL(url)
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
                showResponse(parseJSONWithJSONObject(response.toString()))
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun sendRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(url)
                    .build()

                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    showResponse(parseJSONWithGson(responseData))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun sendRequestWithOkHttpAndCallback() {
        HttpUtil.sendOkHttpRequest(url, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                showResponse(e.message.toString())
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                //回调函数里如果要进行UI界面相关的操作，必须先切换到主线程
                showResponse(response.body?.string() ?: "empty response")
            }

        })
    }


    private fun showResponse(responseData: String) {
        // 展示请求结果时，需要切换到主线程执行
        runOnUiThread {
            binding.responseText.text = responseData
        }
    }

    private fun parseJSONWithJSONObject(responseData: String): String {
        try {
            val jsonObject = JSONObject(responseData)
            val slideshow = jsonObject.getJSONObject("slideshow")
            val title = slideshow.getString("title")
            Log.d(TAG, "slideshow title: $title ")
            val slides = slideshow.getJSONArray("slides")
            for (i in 0 until slides.length()) {
                val item = slides.getJSONObject(i)
                val slideTitle = item.getString("title")
                val slideType = item.getString("type")
                Log.d(TAG, "slide $i, title: $slideTitle, type: $slideType")
            }
            return jsonObject.toString(4)
        } catch (e: Exception) {
            e.printStackTrace()
            return "error while parse response json"
        }
    }

    private fun parseJSONWithGson(responseData: String): String {
//        val gson = Gson()
        val gson = GsonBuilder().setPrettyPrinting().create()
        val response = gson.fromJson(responseData, SlideShowResponse::class.java)
        val slideShow = response.slideshow
        Log.d(TAG, "SlideShow title: ${slideShow.title}")
        Log.d(TAG, "SlideShow title: ${slideShow.date}")
        Log.d(TAG, "SlideShow title: ${slideShow.author}")
        for (slide: Slide in slideShow.slides) {
            Log.d(TAG, "Slide title: ${slide.title}")
            Log.d(TAG, "Slide type: ${slide.type}")
            slide.items?.let {
                for (item in it) {
                    Log.d(TAG, "Slide Item: $item")
                }
            }
        }
        return gson.toJson(slideShow)
    }

    private suspend fun request(address: String): String {
        Log.d(TAG, "request method in ${Thread.currentThread().name}")
        return suspendCoroutine {
            Log.d(TAG, "suspendCoroutine method in ${Thread.currentThread().name}")
            HttpUtil.sendHttpRequest(address, object : HttpCallbackListener {
                override fun onFinish(response: String) {
                    it.resume(response)
                }

                override fun onError(e: Exception) {
                    it.resumeWithException(e)
                }
            }
            )
        }

    }

    suspend fun getBaiduResponse(): String {
        try {
            val response = request("https://www.baidu.com")
            return response
        } catch (e: Exception) {
            e.printStackTrace()
            return e.message.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 取消所有协程
        cancel()
    }

}