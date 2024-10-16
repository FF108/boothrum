package com.isenap5.boothrum.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://safebooru.donmai.us"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val imageBoardService: ImageBoardService by lazy {
        retrofit.create(ImageBoardService::class.java)
    }
}