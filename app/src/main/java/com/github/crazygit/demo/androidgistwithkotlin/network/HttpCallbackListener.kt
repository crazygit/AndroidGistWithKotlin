package com.github.crazygit.demo.androidgistwithkotlin.network

interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}