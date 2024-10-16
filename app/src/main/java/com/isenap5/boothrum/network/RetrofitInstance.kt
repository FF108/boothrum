package com.isenap5.boothrum.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    var BASE_URL = "https://safebooru.donmai.us"
    val api: ImageBoardService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ImageBoardService::class.java)
    }
}
