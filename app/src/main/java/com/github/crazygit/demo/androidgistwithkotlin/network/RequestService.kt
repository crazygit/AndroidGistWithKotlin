package com.github.crazygit.demo.androidgistwithkotlin.network

import retrofit2.Call
import retrofit2.http.GET


interface RequestService {

    @GET("json")
    fun getJsonData(): Call<SlideShowResponse>

}