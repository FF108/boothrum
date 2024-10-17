package com.isenap5.boothrum.network


import com.isenap5.boothrum.domain.model.ImageBoard
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

var BASE_URL =
    "https://safebooru.donmai.us/"

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
@OptIn(ExperimentalSerializationApi::class)
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface ImageBoardApiService {
    @GET("posts.json")
    suspend fun getPosts(): List<ImageBoard>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ImageBoardApi {
    val retrofitService: ImageBoardApiService by lazy {
        retrofit.create(ImageBoardApiService::class.java)
    }
}
